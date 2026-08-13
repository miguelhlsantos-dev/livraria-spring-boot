package com.exemplo.livraria.service;

import com.exemplo.livraria.entity.Livro;
import com.exemplo.livraria.exception.LivroNaoEncontradoException;
import com.exemplo.livraria.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public Livro cadastrar(Livro livro){
    return livroRepository.save(livro);
    }

    public List<Livro> listarTodos(){
        return livroRepository.findAll();
    }

    public Livro buscarPorID(Long id){
        return livroRepository.findById(id).orElseThrow(
                () -> new LivroNaoEncontradoException("Livro não encontrado")
        );
    }

    public void deletarPorID(Long id){
        livroRepository.deleteById(id);
    }

    public void atualizarLivro(Livro livro, Long id)
    {
           Livro livroParaAlterar = livroRepository.findById(id).orElseThrow(
                   () -> new LivroNaoEncontradoException("Livro não encontrado"));
            livroParaAlterar.setAno(livro.getAno());
            livroParaAlterar.setAutor(livro.getAutor());
            livroParaAlterar.setTitulo(livro.getTitulo());
            livroRepository.save(livroParaAlterar);

    }

}
