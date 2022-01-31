package com.netcracker.controller;

import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Client;
import com.netcracker.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ClientController {

    @Autowired
    ClientRepository repository;

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {

       Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client not found for id: " + id)
        );

        return ResponseEntity.ok(client);
    }

    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client){
        return repository.save(client);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client not found for id: " + id)
        );
        repository.deleteById(id);

        return ResponseEntity.ok("deleted");
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Integer id,
                                                   @RequestBody Client clientDetails) throws ResourceNotFoundException {
        Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client not found for id: " + id)
        );

        client.setFirstName(clientDetails.getFirstName());
        client.setLastName(clientDetails.getLastName());
        client.setEmail(clientDetails.getEmail());

        final Client clientUpdated = repository.save(client);

        return ResponseEntity.ok(clientUpdated);
    }

    @PatchMapping("/clients/{id}")
    public ResponseEntity<Client> updateClientPartially(@PathVariable(value = "id") Integer id,
                                                            @RequestBody Client clientDetails) throws ResourceNotFoundException {
        Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client not found for id: " + id)
        );

        if (clientDetails.getFirstName() != null)
            client.setFirstName(clientDetails.getFirstName());
        if (clientDetails.getLastName() != null)
            client.setLastName(clientDetails.getLastName());
        if (clientDetails.getEmail() != null)
            client.setEmail(clientDetails.getEmail());

        final Client clientUpdated = repository.save(client);

        return ResponseEntity.ok(clientUpdated);
    }



}
