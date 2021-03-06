package com.netcracker.services;

import com.netcracker.FavourDtoForClient;
import com.netcracker.Login;
import com.netcracker.RecordDtoForClient;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Client;
import com.netcracker.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;


    public Client getClientById(Integer id) {

        return clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client is not found for id: " + id)
        );
    }

    public Client getClientOnLogin(Login login) {

        return clientRepository.getClientOnLogin(login.getEmail(), login.getPassword());
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<RecordDtoForClient> getRecordsOfClient(Integer id) {
        return clientRepository.getRecordsOfClient(id);
    }

    public List<FavourDtoForClient> getFavoursOfCategory(Integer id) {
        return clientRepository.getFavoursOfCategory(id);
    }


    public void createClient(Client client) {
        clientRepository.save(client);
    }

    public void deleteClientById(Integer id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Client is not found for id: " + id);
        }
    }

    public void updateClient(Integer id, Client newClient) {

        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client is not found for id: " + id)
        );


        client.setFirstName(newClient.getFirstName());
        client.setLastName(newClient.getLastName());
        client.setEmail(newClient.getEmail());
        client.setPassword(newClient.getPassword());


        clientRepository.save(client);
    }

    public void updateClientPartially(Integer id, Client newClient) {

        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client is not found for id: " + id)
        );

        if (newClient.getFirstName() != null)
            client.setFirstName(newClient.getFirstName());
        if (newClient.getLastName() != null)
            client.setLastName(newClient.getLastName());
        if (newClient.getEmail() != null)
            client.setEmail(newClient.getEmail());
        if (newClient.getPassword() != null)
            client.setPassword(newClient.getPassword());

        clientRepository.save(client);
    }
}
