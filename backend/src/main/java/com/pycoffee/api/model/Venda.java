package com.pycoffee.api.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;
    private BigDecimal valorTotal;
    private String metodoPagamento;
    private String status;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Venda() {}

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
