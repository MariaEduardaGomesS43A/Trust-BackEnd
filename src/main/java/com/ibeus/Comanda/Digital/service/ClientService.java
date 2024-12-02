package com.ibeus.Comanda.Digital.service;

import com.ibeus.Comanda.Digital.DTO.client.ClientRequestDTO;
import com.ibeus.Comanda.Digital.DTO.client.ClientResponseDTO;
import com.ibeus.Comanda.Digital.exception.ClientNotFoundException;
import com.ibeus.Comanda.Digital.model.Client;
import com.ibeus.Comanda.Digital.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;


    public List<ClientResponseDTO> findAll() {
        List<Client> clients = repository.findAll();
        return  clients.stream().map(client -> new ClientResponseDTO(client.getId(),client.getName(), client.getPhone(),client.getCep(),
                client.getNumber(), client.getOrderList())).toList();
    }

    public ClientResponseDTO findById(Long id) {
         Client client = repository.findById(id).orElseThrow(ClientNotFoundException::new);
         return new ClientResponseDTO(client.getId(), client.getName(), client.getPhone(), client.getCep(),client.getNumber(),client.getOrderList());
    }

    public ClientResponseDTO create(ClientRequestDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.name());
        client.setPhone(clientDTO.phone());
        client.setCep(clientDTO.cep());
        client.setNumber(clientDTO.number());
        repository.save(client);
        return new ClientResponseDTO(client.getId(),client.getName(),client.getPhone(),client.getCep(), client.getNumber(),client.getOrderList());
    }

    public ClientResponseDTO update(Long id, ClientRequestDTO clientD) {
        Client client = repository.findById(id).orElseThrow(ClientNotFoundException::new);
        client.setName(clientD.name());
        client.setPhone(clientD.phone());
        client.setCep(clientD.cep());
        client.setNumber(clientD.number());
        repository.save(client);
        return new ClientResponseDTO(client.getId(),client.getName(),client.getPhone(), client.getCep(),client.getNumber(), client.getOrderList());

    }

    public void delete(Long id) {
        Client client = repository.findById(id).orElseThrow(ClientNotFoundException::new);
        repository.delete(client);
    }


}
