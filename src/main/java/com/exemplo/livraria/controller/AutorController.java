package com.exemplo.livraria.controller;

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
    public List<Autor> listarTodos(){
        return autorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Autor> listarPorID(@PathVariable Long id){
        return autorService.listarPorID(id);
    }

    @PostMapping
    public Autor cadastrar(@RequestBody @Valid Autor autor){
        return  autorService.cadastrar(autor);
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Long id){
        autorService.deletarPorID(id);
    }

    @PutMapping("/{id}")
    public Autor atualizar(@RequestBody @Valid Autor autor, @PathVariable Long id){
        return autorService.atualizarPorID(autor, id);
    }

}
