package com.netcracker.controller;


import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Master;
import com.netcracker.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class MasterController {

    @Autowired
    MasterRepository repository;

    @GetMapping("/masters")
    public List<Master> getAllMasters() {
        return repository.findAll();
    }

    @GetMapping("/masters/{id}")
    public ResponseEntity<Master> getMasterById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {

        Master master = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master not found for id: " + id)
        );

        return ResponseEntity.ok(master);
    }

    @PostMapping("/masters")
    public Master createMaster(@RequestBody Master master){
        return repository.save(master);
    }

    @DeleteMapping("/masters/{id}")
    public String deleteMaster(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master not found for id: " + id));
        repository.deleteById(id);

        return "deleted";
    }

    @PutMapping("/masters/{id}")
    public ResponseEntity<Master> updateMaster(@PathVariable(value = "id") Integer id,
                                             @RequestBody Master masterDetails) throws ResourceNotFoundException {
       Master master = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master not found for id: " + id));

        master.setFirstName(masterDetails.getFirstName());
        master.setLastName(masterDetails.getLastName());
        master.setEmail(masterDetails.getEmail());
        master.setAdress(masterDetails.getAdress());

        final Master masterUpdated = repository.save(master);

        return ResponseEntity.ok(masterUpdated);

    }

    @PatchMapping("/masters/{id}")
    public ResponseEntity<Master> updatedMasterPartially(@PathVariable(value = "id") Integer id,
                                                      @RequestBody Master masterDetails) throws ResourceNotFoundException {
       Master master = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master not found for id: " + id));

        if (masterDetails.getFirstName() != null)
            master.setFirstName(masterDetails.getFirstName());

        if(masterDetails.getLastName() != null)
            master.setLastName(masterDetails.getLastName());

        if(masterDetails.getEmail() != null)
            master.setEmail(masterDetails.getEmail());

        if(masterDetails.getAdress() != null)
            master.setAdress((masterDetails.getAdress()));

        final Master masterUpdated = repository.save(master);

        return ResponseEntity.ok(masterUpdated);
    }
}
