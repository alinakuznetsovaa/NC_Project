package com.netcracker.utils;

import com.netcracker.dto.ClientDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Client;
import com.netcracker.repositories.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ClientUtil {
    private ModelMapper mapper;

    public ClientUtil(ModelMapper mapper) {
        this.mapper = mapper;
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
