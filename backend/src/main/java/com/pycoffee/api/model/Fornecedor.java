package com.pycoffee.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeEmpresa;
    private String contato;
    private String cnpj;

    public Fornecedor() {}

    public Long getId() {
        return id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public String getContato() {
        return contato;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
