package com.exemplo.livraria.controller;

import com.exemplo.livraria.dto.ClienteRequestDTO;
import com.exemplo.livraria.dto.ClienteResponseDTO;
import com.exemplo.livraria.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> exibirTodos(){
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> exibirPorID(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.listarPorID(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrar(@RequestBody @Valid ClienteRequestDTO clienteDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrarDTO(clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorID(@PathVariable Long id){
        clienteService.deletarPorID(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarPorID(@RequestBody @Valid ClienteRequestDTO clienteDTO, @PathVariable Long id)
    {
        return ResponseEntity.ok(clienteService.atualizar(clienteDTO, id));
    }


}
