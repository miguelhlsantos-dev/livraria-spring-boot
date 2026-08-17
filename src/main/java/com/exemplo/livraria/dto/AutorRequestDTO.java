package com.exemplo.livraria.dto;

import jakarta.validation.constraints.NotBlank;

public class AutorRequestDTO {

    @NotBlank(message = "É obrigatório informar o nome.")
    private String nome;

    @NotBlank(message = "É obrigatório informar a nacionalidade.")
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
