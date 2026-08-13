package com.exemplo.livraria.service;

import com.exemplo.livraria.entity.Autor;
import com.exemplo.livraria.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // GET
    public List<Autor> listarTodos(){
        return autorRepository.findAll();
    }

    // GET /id
    public Optional<Autor> listarPorID(Long id){
        return autorRepository.findById(id);
    }

    // POST
    public Autor cadastrar(Autor autor){
        return autorRepository.save(autor);
    }

    // DELETE
    public void deletarPorID(Long id){
        autorRepository.deleteById(id);
    }

    // PUT
    public Autor atualizarPorID(Autor autor, Long id){
        Autor autorAtual = autorRepository.findById(id).orElseThrow();
        autorAtual.setNome(autor.getNome());
        autorAtual.setNacionalidade(autor.getNacionalidade());
        return autorRepository.save(autorAtual);
    }



}
