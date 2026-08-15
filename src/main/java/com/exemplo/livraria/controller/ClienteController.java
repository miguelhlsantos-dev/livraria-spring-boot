package com.exemplo.livraria.controller;

import com.exemplo.livraria.dto.ClienteRequestDTO;
import com.exemplo.livraria.dto.ClienteResponseDTO;
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
    public List<ClienteResponseDTO> exibirTodos(){
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO exibirPorID(@PathVariable Long id){
        return clienteService.listarPorID(id);
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid ClienteRequestDTO clienteDTO){
        clienteService.cadastrarDTO(clienteDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarPorID(@PathVariable Long id){
        clienteService.deletarPorID(id);
    }

    @PutMapping("/{id}")
    public void atualizarPorID(@RequestBody ClienteRequestDTO clienteDTO, @PathVariable Long id)
    {
        clienteService.atualizar(clienteDTO, id);
    }


}
