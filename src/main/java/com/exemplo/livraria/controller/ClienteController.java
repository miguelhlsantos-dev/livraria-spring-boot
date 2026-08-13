package com.exemplo.livraria.controller;

import com.exemplo.livraria.entity.Cliente;
import com.exemplo.livraria.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> exibirTodos(){
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> exibirPorID(@PathVariable Long id){
        return clienteService.listarPorID(id);
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid Cliente cliente){
        clienteService.cadastrar(cliente);
    }

    @DeleteMapping("/{id}")
    public void deletarPorID(@PathVariable Long id){
        clienteService.deletarPorID(id);
    }

    @PutMapping("/{id}")
    public void atualizarPorID(@RequestBody Cliente cliente, @PathVariable Long id)
    {
        clienteService.atualizar(cliente, id);
    }


}
