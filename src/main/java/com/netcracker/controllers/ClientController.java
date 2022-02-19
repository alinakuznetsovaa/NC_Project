package com.netcracker.controllers;

import com.netcracker.dto.ClientDTO;
import com.netcracker.model.Client;
import com.netcracker.services.ClientService;
import com.netcracker.utils.ClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/rest")
public class ClientController {

    @Autowired
    ClientService clientService;


    @Autowired
    ClientUtil clientUtil;


    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients().stream().map(clientUtil::mapToDTO).collect(toList());
    }

    @GetMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientById(@PathVariable(value = "id") Integer id) {
        Client client = clientService.getClientById(id);
        return clientUtil.mapToDTO(client);
    }


    @GetMapping("/clients/get-records-of-client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllRecordsOfClient(@PathVariable(value = "id") Integer id) {
        return clientService.getRecordsOfClient(id);
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody ClientDTO clientDTO) {

        Client client = clientUtil.mapToEntity(clientDTO);
        clientService.createClient(client);
    }


    @DeleteMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable(value = "id") Integer id) {
        clientService.deleteClientById(id);
    }

    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateClient(@PathVariable(value = "id") Integer id,
                             @RequestBody ClientDTO clientDTO) {
        Client newClient = clientUtil.mapToEntity(clientDTO);
        clientService.updateClient(id, newClient);
    }

    @PatchMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateClientPartially(@PathVariable(value = "id") Integer id,
                                      @RequestBody ClientDTO clientDTO) {
        Client newClient = clientUtil.mapToEntity(clientDTO);
        clientService.updateClientPartially(id, newClient);
    }


}
