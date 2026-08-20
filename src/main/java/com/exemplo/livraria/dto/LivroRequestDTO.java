package com.exemplo.livraria.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class LivroRequestDTO {

    @NotBlank(message = "É obrigatório informar o título.")
    private String titulo;

    private Long autorId;

    @Min(value = 1500, message = "É obrigatório informar a nacionalidade.")
    private int ano;

    private Long editoraId;

    public Long getEditoraId() {
        return editoraId;
    }

    public void setEditoraId(Long editoraId) {
        this.editoraId = editoraId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
