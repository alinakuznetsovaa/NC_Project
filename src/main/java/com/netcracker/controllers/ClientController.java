package com.netcracker.controllers;

import com.netcracker.Rec;
import com.netcracker.dto.ClientDTO;
import com.netcracker.model.Client;
import com.netcracker.services.ClientService;
import com.netcracker.utils.ClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    @Autowired
    ClientService clientService;


    @Autowired
    ClientUtil clientUtil;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<ClientDTO> getAllClients() {
        return (ArrayList<ClientDTO>) clientService.getAllClients().stream().map(clientUtil::mapToDTO).collect(toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientById(@PathVariable(value = "id") Integer id) {
        Client client = clientService.getClientById(id);
        return clientUtil.mapToDTO(client);
    }


    @GetMapping("/get-all-records-of-client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Rec> getAllRecordsOfClient(@PathVariable(value = "id") Integer id) {
        return clientService.getRecordsOfClient(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {

        Client client = clientUtil.mapToEntity(clientDTO);
        clientService.createClient(client);
        return clientUtil.mapToDTO(client);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable(value = "id") Integer id) {
        clientService.deleteClientById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateClient(@PathVariable(value = "id") Integer id,
                             @RequestBody ClientDTO clientDTO) {
        Client newClient = clientUtil.mapToEntity(clientDTO);
        clientService.updateClient(id, newClient);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateClientPartially(@PathVariable(value = "id") Integer id,
                                      @RequestBody ClientDTO clientDTO) {
        Client newClient = clientUtil.mapToEntity(clientDTO);
        clientService.updateClientPartially(id, newClient);
    }


}
