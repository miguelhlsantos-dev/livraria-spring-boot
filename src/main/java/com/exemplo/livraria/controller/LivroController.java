package com.exemplo.livraria.controller;

import com.exemplo.livraria.dto.LivroRequestDTO;
import com.exemplo.livraria.dto.LivroResponseDTO;
import com.exemplo.livraria.entity.Livro;
import com.exemplo.livraria.service.LivroService;
import jakarta.validation.Valid;
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

//    @PostMapping
//    public Livro cadastrar(@RequestBody @Valid Livro livro){
//        return livroService.cadastrar(livro);
//    }

    @PostMapping
    public void cadastrarDTO(@RequestBody @Valid LivroRequestDTO livroDto){
        livroService.cadastrarDTO(livroDto);
    }

    @GetMapping
    public List<LivroResponseDTO> exibirTodos(){
        return livroService.listarTodos();
    }

//    @GetMapping("/{id}")
//    public Livro exibirPorId(@PathVariable Long id){
//        return livroService.buscarPorID(id);
//    }

    @GetMapping("/{id}")
    public LivroResponseDTO exibirPorID(@PathVariable Long id){
        return livroService.buscarPorID(id);
    }

//    @DeleteMapping("/{id}")
//    public void deletarPorID(@PathVariable Long id){
//        livroService.deletarPorID(id);
//    }


    @PutMapping("/{id}")
    public void atualizarPorID(@RequestBody LivroRequestDTO livrodto, @PathVariable Long id){
        livroService.atualizarDTO(livrodto, id);
    }
}
