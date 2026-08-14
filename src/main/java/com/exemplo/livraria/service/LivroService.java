package com.exemplo.livraria.service;

import com.exemplo.livraria.dto.LivroRequestDTO;
import com.exemplo.livraria.entity.Autor;
import com.exemplo.livraria.entity.Livro;
import com.exemplo.livraria.exception.AutorNaoEncontradoException;
import com.exemplo.livraria.exception.LivroNaoEncontradoException;
import com.exemplo.livraria.repository.AutorRepository;
import com.exemplo.livraria.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository){
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }
//    metodo antigo usando a entidade livro
//    public Livro cadastrar(Livro livro){
//    return livroRepository.save(livro);
//    }

    public void cadastrarDTO(LivroRequestDTO livroDTO){
        Long idAutorDTo = livroDTO.getAutorId();
        Autor autorLivroDTO = autorRepository.findById(idAutorDTo)
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado"));
        Livro livroConvertido = new Livro();
        livroConvertido.setAno(livroDTO.getAno());
        livroConvertido.setAutor(autorLivroDTO);
        livroConvertido.setTitulo(livroDTO.getTitulo());
        livroRepository.save(livroConvertido);
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
