package com.netcracker.services;

import com.netcracker.dto.ClientDTO;
import com.netcracker.model.Client;
import com.netcracker.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private ModelMapper mapper;
    private ClientRepository clientRepository;

    public ClientService(ModelMapper mapper, ClientRepository clientRepository) {
        this.mapper = mapper;
        this.clientRepository = clientRepository;
    }

    public ClientDTO mapToDTO(Client client){
        ClientDTO clientDTO = mapper.map(client, ClientDTO.class);
        return clientDTO;
    }

    public Client mapToEntity(ClientDTO clientDTO){
        Client client = mapper.map(clientDTO, Client.class);
        return client;
    }
}
