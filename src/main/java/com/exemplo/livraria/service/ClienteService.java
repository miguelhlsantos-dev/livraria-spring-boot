package com.exemplo.livraria.service;

import com.exemplo.livraria.entity.Cliente;
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

    public List<Cliente> listarTodos(){
       return clienteRepository.findAll();
    }

    public Optional<Cliente> listarPorID(Long id){
        return clienteRepository.findById(id);
    }

    // cadastro - POST
    public void cadastrar(Cliente cliente){
        clienteRepository.save(cliente);
    }

    // deletar - DELETE
    public void deletarPorID(Long id){
        clienteRepository.deleteById(id);
    }

    // atualizar - PUT
    public Cliente atualizar(Cliente cliente, Long id){
        Cliente clienteAtual = clienteRepository.findById(id).orElseThrow();
        clienteAtual.setEmail(cliente.getEmail());
        clienteAtual.setNome(cliente.getNome());
        clienteAtual.setIdade(cliente.getIdade());
        return clienteRepository.save(clienteAtual);
    }

}
