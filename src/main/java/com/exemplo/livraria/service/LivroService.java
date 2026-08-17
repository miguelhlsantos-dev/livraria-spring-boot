package com.exemplo.livraria.service;

import com.exemplo.livraria.dto.LivroRequestDTO;
import com.exemplo.livraria.dto.LivroResponseDTO;
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

    private LivroResponseDTO livroToLivroDTO(Livro livro){
        LivroResponseDTO livroDTO = new LivroResponseDTO();
        livroDTO.setTitulo(livro.getTitulo());
        livroDTO.setId(livro.getId());
        livroDTO.setAno(livro.getAno());
        livroDTO.setAutor(livro.getAutor().getNome());
        return livroDTO;
    }

    public LivroResponseDTO cadastrarDTO(LivroRequestDTO livroDTO){
        Long idAutorDTo = livroDTO.getAutorId();
        Autor autorLivroDTO = autorRepository.findById(idAutorDTo)
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado"));
        Livro livroConvertido = new Livro();
        livroConvertido.setAno(livroDTO.getAno());
        livroConvertido.setAutor(autorLivroDTO);
        livroConvertido.setTitulo(livroDTO.getTitulo());
        livroRepository.save(livroConvertido);
        return livroToLivroDTO(livroConvertido);
    }


    public List<LivroResponseDTO> listarTodos(){
        List<Livro> lista = livroRepository.findAll();
        return lista.stream().map(this::livroToLivroDTO).toList();
    }

    public LivroResponseDTO buscarPorID(Long id){
        Livro livroPreDTO = livroRepository.findById(id)
                .orElseThrow( () -> new LivroNaoEncontradoException("Livro não encontrado"));
        return livroToLivroDTO(livroPreDTO);
    }

    public void deletarPorID(Long id){
        livroRepository.deleteById(id);
    }

    public LivroResponseDTO atualizarDTO(LivroRequestDTO livroDTO, Long id){
        Livro livroParaAlterar = livroRepository.findById(id)
                .orElseThrow( () -> new LivroNaoEncontradoException("Livro não encontrado"));
        Autor autor = autorRepository.findById(livroParaAlterar.getAutor().getId())
                .orElseThrow( () -> new AutorNaoEncontradoException("Autor não encontrado"));
        livroParaAlterar.setTitulo(livroDTO.getTitulo());
        livroParaAlterar.setAno(livroDTO.getAno());
        livroParaAlterar.setAutor(autor);
        livroRepository.save(livroParaAlterar);
        return livroToLivroDTO(livroParaAlterar);
    }

}
