package com.netcracker.controller;

import com.netcracker.dto.MasterDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Master;
import com.netcracker.repository.MasterRepository;
import com.netcracker.services.MasterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/rest")
public class MasterController {

    @Autowired
    MasterRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MasterService masterService;

    @GetMapping("/masters")
    @ResponseStatus(HttpStatus.OK)
    public List<MasterDTO> getAllMasters() {
        return repository.findAll().stream().map(masterService::mapToDTO).collect(toList());
    }

    @GetMapping("/masters/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MasterDTO getMasterById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {

        Master master = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master is not found for id: " + id)
        );
        return masterService.mapToDTO(master);
    }


    @GetMapping("/masters/get-records-of-master/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<List<String>> getRecordsOfMaster(@PathVariable(value = "id") Integer id) {
        return repository.getRecordsOfMaster(id);
    }

    @PostMapping("/masters")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMaster(@RequestBody MasterDTO masterDTO){
         Master master = masterService.mapToEntity(masterDTO);
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
                                             @RequestBody MasterDTO masterDetails) throws ResourceNotFoundException {
       Master master = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master is not found for id: " + id));

        master.setFirstName(masterDetails.getFirstName());
        master.setLastName(masterDetails.getLastName());
        master.setEmail(masterDetails.getEmail());
        master.setAddress(masterDetails.getAddress());

        repository.save(master);

    }

    @PatchMapping("/masters/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatedMasterPartially(@PathVariable(value = "id") Integer id,
                                                      @RequestBody MasterDTO masterDetails) throws ResourceNotFoundException {
       Master master = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master is not found for id: " + id));

        if (masterDetails.getFirstName() != null)
            master.setFirstName(masterDetails.getFirstName());

        if(masterDetails.getLastName() != null)
            master.setLastName(masterDetails.getLastName());

        if(masterDetails.getEmail() != null)
            master.setEmail(masterDetails.getEmail());

        if(masterDetails.getAddress() != null)
            master.setAddress((masterDetails.getAddress()));

        repository.save(master);

    }
}
