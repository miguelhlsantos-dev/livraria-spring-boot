package com.exemplo.livraria.service;

import com.exemplo.livraria.dto.EditoraRequestDTO;
import com.exemplo.livraria.dto.EditoraResponseDTO;
import com.exemplo.livraria.entity.Editora;
import com.exemplo.livraria.exception.EditoraNaoEncontradaException;
import com.exemplo.livraria.repository.EditoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraService {

    private EditoraRepository editoraRepository;

    public EditoraService(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    private EditoraResponseDTO editoraToDTO(Editora editora){
        EditoraResponseDTO editoraDTO = new EditoraResponseDTO();
        editoraDTO.setNome(editora.getNome());
        editoraDTO.setId(editora.getId());
        editoraDTO.setCidade(editora.getCidade());
        return editoraDTO;
    }

    // será usado no GET e retorna uma lista de ResponseDTO
    public List<EditoraResponseDTO> listarTodos(){
        List<Editora> lista = editoraRepository.findAll();
        return lista.stream().map(this::editoraToDTO).toList();
    }

    // será usado no GET/id e retorna um objeto ResponseDTO
    public EditoraResponseDTO listarPorID(Long id){
        Editora editora = editoraRepository.findById(id)
                .orElseThrow( () -> new EditoraNaoEncontradaException("Editora não encontrada"));
        return editoraToDTO(editora);
    }

    // será usado no POST e retorna um ResponseDTO
    public EditoraResponseDTO cadastrar(EditoraRequestDTO editoraRequestDTO){
        Editora editora = new Editora();
        editora.setNome(editoraRequestDTO.getNome());
        editora.setCidade(editoraRequestDTO.getCidade());
        editoraRepository.save(editora);
        return editoraToDTO(editora);
    }

    // será usado no DELETE e não possui retorno
    public void deletar(Long id){
        Editora editoraParaDeletar = editoraRepository.findById(id)
                .orElseThrow(()-> new EditoraNaoEncontradaException("Editora não encontrada"));
        editoraRepository.delete(editoraParaDeletar);
    }

    // será usado no PUT e retorna um ResponseDTO
    public EditoraResponseDTO atualizar(EditoraRequestDTO editoraDTO, Long id){
        Editora editoraParaAtualizar = editoraRepository.findById(id)
                .orElseThrow( () -> new EditoraNaoEncontradaException("Editora não encontrada"));
        editoraParaAtualizar.setCidade(editoraDTO.getCidade());
        editoraParaAtualizar.setNome(editoraDTO.getNome());
        editoraRepository.save(editoraParaAtualizar);
        return editoraToDTO(editoraParaAtualizar);
    }


}
