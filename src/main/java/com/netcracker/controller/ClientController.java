package com.netcracker.controller;

import com.netcracker.dto.ClientDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Client;
import com.netcracker.repository.ClientRepository;
import com.netcracker.services.ClientService;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/rest")
public class ClientController {

    @Autowired
    ClientRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientService clientService;



    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> getAllClients() {
        return repository.findAll().stream().map(clientService::mapToDTO).collect(toList());
    }

    @GetMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {

       Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client is not found for id: " + id)
        );

        return clientService.mapToDTO(client);
    }

    @GetMapping("/clients/get-records-of-client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<List<String>> getAllRecordsOfClient(@PathVariable(value = "id") Integer id) {
        return repository.getRecordsOfClient(id);
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody ClientDTO clientDTO){
        Client client = clientService.mapToEntity(clientDTO);
        repository.save(client);
    }


    @DeleteMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
       try{
           repository.deleteById(id);
       }catch (EmptyResultDataAccessException e){
           throw new ResourceNotFoundException("Client is not found for id: " + id);
       }

    }

    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateClient(@PathVariable(value = "id") Integer id,
                                                   @RequestBody ClientDTO clientDetails) throws ResourceNotFoundException {
        Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client is not found for id: " + id)
        );

        client.setFirstName(clientDetails.getFirstName());
        client.setLastName(clientDetails.getLastName());
        client.setEmail(clientDetails.getEmail());

        repository.save(client);
    }

    @PatchMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateClientPartially(@PathVariable(value = "id") Integer id,
                                                            @RequestBody ClientDTO clientDetails) throws ResourceNotFoundException {
        Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client is not found for id: " + id)
        );

        if (clientDetails.getFirstName() != null)
            client.setFirstName(clientDetails.getFirstName());
        if (clientDetails.getLastName() != null)
            client.setLastName(clientDetails.getLastName());
        if (clientDetails.getEmail() != null)
            client.setEmail(clientDetails.getEmail());

        repository.save(client);
    }



}
