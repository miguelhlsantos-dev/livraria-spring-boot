package com.exemplo.livraria.controller;

import com.exemplo.livraria.dto.AutorRequestDTO;
import com.exemplo.livraria.dto.AutorResponseDTO;
import com.exemplo.livraria.entity.Autor;
import com.exemplo.livraria.service.AutorService;
import jakarta.validation.Valid;
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
    public List<AutorResponseDTO> listarTodos(){
        return autorService.listarTodos();
    }

    @GetMapping("/{id}")
    public AutorResponseDTO listarPorID(@PathVariable Long id){
        return autorService.listarPorID(id);
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid AutorRequestDTO autorDTO){
        autorService.cadastrar(autorDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Long id){
        autorService.deletarPorID(id);
    }

    @PutMapping("/{id}")
    public void atualizar(@RequestBody @Valid AutorRequestDTO autorDTO, @PathVariable Long id){
        autorService.atualizarPorID(autorDTO, id);
    }

}
