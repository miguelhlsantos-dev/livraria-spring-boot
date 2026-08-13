package com.exemplo.livraria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<String> tratarLivroNaoEncontrado(LivroNaoEncontradoException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
    }

    @ExceptionHandler(AutorNaoEncontradoException.class)
    public ResponseEntity<String> tratarAutorNaoEncontrado(AutorNaoEncontradoException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor não encontrado");
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<String> tratarClienteNaoEncontrado (ClienteNaoEncontradoException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

}
