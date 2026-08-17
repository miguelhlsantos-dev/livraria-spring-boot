package com.exemplo.livraria.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ClienteRequestDTO {

    @NotBlank(message = "É obrigatório informar o nome.")
    private String nome;

    @Email(message = "É obrigatório informar um endereço de e-mail válido.")
    private String email;

    @Positive(message = "É obrigatório informar um valor maior que 0 para a idade.")
    private int idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
