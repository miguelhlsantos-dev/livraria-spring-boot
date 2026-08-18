package com.exemplo.livraria.controller;

import com.exemplo.livraria.dto.EditoraRequestDTO;
import com.exemplo.livraria.dto.EditoraResponseDTO;
import com.exemplo.livraria.entity.Editora;
import com.exemplo.livraria.service.EditoraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

    private EditoraService editoraService;

    public EditoraController(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    @GetMapping
    public ResponseEntity<List<EditoraResponseDTO>> listarTodos(){
        return ResponseEntity.ok(editoraService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditoraResponseDTO> listarPorID(@PathVariable Long id){
        return ResponseEntity.ok(editoraService.listarPorID(id));
    }

    @PostMapping
    public ResponseEntity<EditoraResponseDTO> cadastrar(@RequestBody @Valid EditoraRequestDTO editoraDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(editoraService.cadastrar(editoraDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        editoraService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditoraResponseDTO> atualizarPorID(@RequestBody @Valid EditoraRequestDTO editoraRequestDTO,
                                                             @PathVariable Long id ){
        return ResponseEntity.ok(editoraService.atualizar(editoraRequestDTO, id));
    }

}
