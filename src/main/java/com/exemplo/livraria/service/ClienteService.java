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
        ClienteResponseDTO clientrDTO = new ClienteResponseDTO();
        clientrDTO.setEmail(cliente.getEmail());
        clientrDTO.setNome(cliente.getNome());
        clientrDTO.setIdade(cliente.getIdade());
        clientrDTO.setId(cliente.getId());
        return clientrDTO;
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

//    cadastro - POST
//    public void cadastrar(Cliente cliente){
//        clienteRepository.save(cliente);
//    }

    public void cadastrarDTO(ClienteRequestDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        clienteRepository.save(cliente);
    }

    // deletar - DELETE
    public void deletarPorID(Long id){
        clienteRepository.deleteById(id);
    }

    // atualizar - PUT
    public void atualizar(ClienteRequestDTO clienteDTO, Long id){
        Cliente cliente = clienteRepository.findById(id).
                orElseThrow( () -> new ClienteNaoEncontradoException("Cliente não encontrado"));
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setNome(clienteDTO.getNome());
        clienteRepository.save(cliente);
    }

}
