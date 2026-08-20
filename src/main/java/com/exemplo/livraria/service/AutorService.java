package com.exemplo.livraria.service;

import com.exemplo.livraria.dto.AutorRequestDTO;
import com.exemplo.livraria.dto.AutorResponseDTO;
import com.exemplo.livraria.entity.Autor;
import com.exemplo.livraria.entity.Livro;
import com.exemplo.livraria.exception.AutorNaoEncontradoException;
import com.exemplo.livraria.exception.LivroNaoEncontradoException;
import com.exemplo.livraria.exception.NegocioException;
import com.exemplo.livraria.repository.AutorRepository;
import com.exemplo.livraria.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private AutorRepository autorRepository;
    private LivroRepository livroRepository;

    public AutorService(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    private AutorResponseDTO autorParaDTO(Autor autor) {
        AutorResponseDTO autorDTO = new AutorResponseDTO();
        autorDTO.setNacionalidade(autor.getNacionalidade());
        autorDTO.setNome(autor.getNome());
        autorDTO.setId(autor.getId());
        return autorDTO;
    }

    // GET
    public List<AutorResponseDTO> listarTodos() {
        List<Autor> lista = autorRepository.findAll();
        return lista.stream()
                .map(this::autorParaDTO)
                .toList();
    }

    // GET /id
    public AutorResponseDTO listarPorID(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado"));
        return autorParaDTO(autor);
    }

    // POST
    public AutorResponseDTO cadastrar(AutorRequestDTO autorDTO) {
        Autor autor = new Autor();
        autor.setNacionalidade(autorDTO.getNacionalidade());
        autor.setNome(autorDTO.getNome());
        autorRepository.save(autor);
        return autorParaDTO(autor);
    }

    // DELETE
    public void deletarPorID(Long id) {

        if (livroRepository.existsByAutorId(id)) {
            throw new NegocioException("Não é possível excluir um autor que possui livros associados a ele.");
        }

        Autor autorParaExcluir = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado"));
        autorRepository.delete(autorParaExcluir);
    }

    // PUT
    public AutorResponseDTO atualizarPorID(AutorRequestDTO autorDTO, Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado"));
        autor.setNome(autorDTO.getNome());
        autor.setNacionalidade(autorDTO.getNacionalidade());
        autorRepository.save(autor);
        return autorParaDTO(autor);
    }


}
