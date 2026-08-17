package com.pycoffee.api.controller;

import com.pycoffee.api.model.ItemVenda;
import com.pycoffee.api.model.Produto;
import com.pycoffee.api.model.Venda;
import com.pycoffee.api.repository.ProdutoRepository;
import com.pycoffee.api.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<String> registrarVenda(@RequestBody Venda venda){
        venda.setDataHora(LocalDateTime.now());
        venda.setStatus("CONCLUIDA");
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemVenda item : venda.getItens()){
            Produto produto = produtoRepository.findById(item.getProduto().getId()).orElse(null);
            if (produto.getQuantidadeEstoque() < item.getQuantidade()){
                return ResponseEntity.badRequest().body("Estoque insuficiente para o produto: " + produto.getNome());
            }

            item.setPrecoUnitario(produto.getPrecoVenda());
            BigDecimal subtotal = produto.getPrecoVenda().multiply(new BigDecimal(item.getQuantidade()));
            item.setSubtotal(subtotal);
            valorTotal = valorTotal.add(subtotal);

            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());

            item.setVenda(venda);
        }

        venda.setValorTotal(valorTotal);
        vendaRepository.save(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body("Venda registrada com sucesso! Valor total: R$ " + valorTotal);
    }
}
