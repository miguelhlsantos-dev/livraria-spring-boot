package com.exemplo.livraria.service;

import com.exemplo.livraria.dto.ClienteRequestDTO;
import com.exemplo.livraria.dto.ClienteResponseDTO;
import com.exemplo.livraria.entity.Cliente;
import com.exemplo.livraria.exception.ClienteNaoEncontradoException;
import com.exemplo.livraria.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    private ClienteResponseDTO transformarClienteDTO(Cliente cliente){
        ClienteResponseDTO clienteDTO = new ClienteResponseDTO();
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setIdade(cliente.getIdade());
        clienteDTO.setId(cliente.getId());
        return clienteDTO;
    }

    public List<ClienteResponseDTO> listarTodos(){
       List<Cliente> lista = clienteRepository.findAll();
       return lista.stream().map(this::transformarClienteDTO).toList();
    }

    public ClienteResponseDTO listarPorID(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow( () -> new ClienteNaoEncontradoException("Cliente não encontrado"));
        return transformarClienteDTO(cliente);
    }

    public ClienteResponseDTO cadastrarDTO(ClienteRequestDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        clienteRepository.save(cliente);
        return transformarClienteDTO(cliente);
    }

    // deletar - DELETE
    public void deletarPorID(Long id){
        clienteRepository.deleteById(id);
    }

    // atualizar - PUT
    public ClienteResponseDTO atualizar(ClienteRequestDTO clienteDTO, Long id){
        Cliente cliente = clienteRepository.findById(id).
                orElseThrow( () -> new ClienteNaoEncontradoException("Cliente não encontrado"));
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setNome(clienteDTO.getNome());
        clienteRepository.save(cliente);
        return transformarClienteDTO(cliente);
    }

}
