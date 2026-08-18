package com.exemplo.livraria.dto;

import jakarta.validation.constraints.NotBlank;

public class EditoraRequestDTO {

    @NotBlank(message = "É obrigatório informar o nome.")
    private String nome;

    @NotBlank(message = "É obrigatório informar a cidade.")
    private String cidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
