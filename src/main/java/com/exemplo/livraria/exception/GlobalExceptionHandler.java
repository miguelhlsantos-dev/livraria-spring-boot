package com.exemplo.livraria.exception;

import com.exemplo.livraria.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> tratarArgumentosInvalidos(MethodArgumentNotValidException exception){
        ErrorResponseDTO erro = new ErrorResponseDTO();
        List<FieldError> lista = exception.getBindingResult().getFieldErrors();
        Map<String, String> erros = new HashMap<>();

        for (FieldError erroNaLista : lista){
            erros.put(
                    erroNaLista.getField(), erroNaLista.getDefaultMessage()
            );
        }

        erro.setMensagem("Erro de validação");
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setTimestamp(LocalDateTime.now());
        erro.setErros(erros);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    @ExceptionHandler(EditoraNaoEncontradaException.class)
    public ResponseEntity<ErrorResponseDTO> tratarEditoraNaoEncontrada(EditoraNaoEncontradaException exception){
        ErrorResponseDTO erro = new ErrorResponseDTO();
        erro.setTimestamp(LocalDateTime.now());
        erro.setMensagem(exception.getMessage());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ErrorResponseDTO> tratarRegraDeNegocio(NegocioException exception){
        ErrorResponseDTO erro = new ErrorResponseDTO();
        erro.setTimestamp(LocalDateTime.now());
        erro.setMensagem(exception.getMessage());
        erro.setStatus(HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

}
