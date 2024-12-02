package com.ibeus.Comanda.Digital.controller;

import com.ibeus.Comanda.Digital.DTO.client.ClientRequestDTO;
import com.ibeus.Comanda.Digital.DTO.client.ClientResponseDTO;
import com.ibeus.Comanda.Digital.model.Client;
import com.ibeus.Comanda.Digital.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @Operation(summary = "FindAll", description = "Esse metodo retorna todos os clients")
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> findAll() {
        List<ClientResponseDTO> clients = service.findAll();
        return ResponseEntity.ok().body(clients);
    }

    @Operation(summary = "FindById", description = "Esse metodo retorna o client por id")
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) {
        ClientResponseDTO client = service.findById(id);
        return ResponseEntity.ok().body(client);
    }
    @Operation(summary = "createClient", description = "Esse metodo faz a criação do client")
    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody ClientRequestDTO client) {
        ClientResponseDTO clientD = service.create(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(clientD.id()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "updateClient", description = "Esse metodo faz a atualização do client")
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id, @RequestBody ClientRequestDTO client) {
        ClientResponseDTO clientResponseDTO = service.update(id, client);
        return ResponseEntity.ok().body(clientResponseDTO);
    }

    @Operation(summary = "deleteClient", description = "Esse metodo faz a deleção do client")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
