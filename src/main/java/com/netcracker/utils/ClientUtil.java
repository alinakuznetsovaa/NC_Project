package com.netcracker.utils;

import com.netcracker.dto.ClientDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Client;
import com.netcracker.repositories.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientUtil {
    private ModelMapper mapper;
    private ClientRepository clientRepository;

    public ClientUtil(ModelMapper mapper, ClientRepository clientRepository) {
        this.mapper = mapper;
        this.clientRepository = clientRepository;
    }

    public ClientDTO mapToDTO(Client client) {
        try {
            return mapper.map(client, ClientDTO.class);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Client is not found");
        }
    }

    public Client mapToEntity(ClientDTO clientDTO) {
        return mapper.map(clientDTO, Client.class);
    }
}
