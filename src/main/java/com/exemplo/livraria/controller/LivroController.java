package com.exemplo.livraria.controller;

import com.exemplo.livraria.dto.LivroRequestDTO;
import com.exemplo.livraria.dto.LivroResponseDTO;
import com.exemplo.livraria.entity.Livro;
import com.exemplo.livraria.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private LivroService livroService;

    public LivroController(LivroService livroService){
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> cadastrarDTO(@RequestBody @Valid LivroRequestDTO livroDto){
        return ResponseEntity.ok(livroService.cadastrarDTO(livroDto));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> exibirTodos(){
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> exibirPorID(@PathVariable Long id){
        return ResponseEntity.ok(livroService.buscarPorID(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorID(@PathVariable Long id){
        livroService.deletarPorID(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizarPorID(@RequestBody @Valid LivroRequestDTO livrodto, @PathVariable Long id){
        LivroResponseDTO livroAtualizado = livroService.atualizarDTO(livrodto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroAtualizado);
    }
}
