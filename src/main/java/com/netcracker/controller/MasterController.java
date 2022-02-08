package com.netcracker.controller;

import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Master;
import com.netcracker.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class MasterController {

    @Autowired
    MasterRepository repository;

    @GetMapping("/masters")
    @ResponseStatus(HttpStatus.OK)
    public List<Master> getAllMasters() {
        return repository.findAll();
    }

    @GetMapping("/masters/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Master getMasterById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {

        Master master = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master is not found for id: " + id)
        );
        return master;
    }

    @PostMapping("/masters")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMaster(@RequestBody Master master){
         repository.save(master);
    }


    @DeleteMapping("/masters/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaster(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Master is not found for id: " + id);
        }
    }

    @PutMapping("/masters/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMaster(@PathVariable(value = "id") Integer id,
                                             @RequestBody Master masterDetails) throws ResourceNotFoundException {
       Master master = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master is not found for id: " + id));

        master.setFirstName(masterDetails.getFirstName());
        master.setLastName(masterDetails.getLastName());
        master.setEmail(masterDetails.getEmail());
        master.setAdress(masterDetails.getAdress());

        repository.save(master);

    }

    @PatchMapping("/masters/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatedMasterPartially(@PathVariable(value = "id") Integer id,
                                                      @RequestBody Master masterDetails) throws ResourceNotFoundException {
       Master master = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master is not found for id: " + id));

        if (masterDetails.getFirstName() != null)
            master.setFirstName(masterDetails.getFirstName());

        if(masterDetails.getLastName() != null)
            master.setLastName(masterDetails.getLastName());

        if(masterDetails.getEmail() != null)
            master.setEmail(masterDetails.getEmail());

        if(masterDetails.getAdress() != null)
            master.setAdress((masterDetails.getAdress()));

        repository.save(master);

    }
}
