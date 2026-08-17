package com.exemplo.livraria.controller;

import com.exemplo.livraria.dto.AutorRequestDTO;
import com.exemplo.livraria.dto.AutorResponseDTO;
import com.exemplo.livraria.entity.Autor;
import com.exemplo.livraria.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> listarTodos(){
        return ResponseEntity.ok().body(autorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> listarPorID(@PathVariable Long id){
        return ResponseEntity.ok(autorService.listarPorID(id));
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> cadastrar(@RequestBody @Valid AutorRequestDTO autorDTO){
        AutorResponseDTO autorCadastrado = autorService.cadastrar(autorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorCadastrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        autorService.deletarPorID(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> atualizar(@RequestBody @Valid AutorRequestDTO autorDTO, @PathVariable Long id){
        return ResponseEntity.ok().body(autorService.atualizarPorID(autorDTO, id));
    }
}
