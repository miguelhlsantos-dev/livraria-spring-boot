package com.exemplo.livraria.dto;

import jakarta.validation.constraints.NotBlank;

public class AutorRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String nacionalidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
