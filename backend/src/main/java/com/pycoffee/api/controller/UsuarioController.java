package com.pycoffee.api.controller;

import com.pycoffee.api.model.Usuario;
import com.pycoffee.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario credenciais) {

        Optional<Usuario> usuarioLogado = usuarioRepository.findByLoginAndSenha(
                credenciais.getLogin(),
                credenciais.getSenha()
        );

        if (usuarioLogado.isPresent()) {
            return ResponseEntity.ok("Login realizado com sucesso! Bem-vindo, " + usuarioLogado.get().getNome());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha incorretos.");
    }
}
