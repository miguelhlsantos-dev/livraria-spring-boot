package com.exemplo.livraria.exception;

import com.exemplo.livraria.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> tratarLivroNaoEncontrado(LivroNaoEncontradoException exception){
        ErrorResponseDTO erro = new ErrorResponseDTO();
        erro.setTimestamp(LocalDateTime.now());
        erro.setMensagem(exception.getMessage());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(AutorNaoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> tratarAutorNaoEncontrado(AutorNaoEncontradoException exception){
       ErrorResponseDTO erro = new ErrorResponseDTO();
       erro.setMensagem(exception.getMessage());
       erro.setStatus(HttpStatus.NOT_FOUND.value());
       erro.setTimestamp(LocalDateTime.now());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }


    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> tratarClienteNaoEncontrado (ClienteNaoEncontradoException exception){
        ErrorResponseDTO erro = new ErrorResponseDTO();
        erro.setMensagem(exception.getMessage());
        erro.setTimestamp(LocalDateTime.now());
        erro.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
