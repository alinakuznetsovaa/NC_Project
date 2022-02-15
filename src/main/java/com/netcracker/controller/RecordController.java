package com.netcracker.controller;

import com.netcracker.dto.RecordDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Client;
import com.netcracker.model.Master;
import com.netcracker.model.Record;
import com.netcracker.model.Service;
import com.netcracker.repository.ClientRepository;
import com.netcracker.repository.MasterRepository;
import com.netcracker.repository.RecordRepository;
import com.netcracker.repository.ServiceRepository;
import com.netcracker.services.RecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/rest")
public class RecordController {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    MasterRepository masterRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RecordService recordService;



    @GetMapping("/records")
    @ResponseStatus(HttpStatus.OK)
    public List<RecordDTO> getAllRecords() {
        return recordRepository.findAll().stream().map(recordService::mapToDTO).collect(toList());
    }

    @GetMapping("/records/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecordDTO getRecordById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {

        Record record = recordRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record is not found for id: " + id)
        );
        return recordService.mapToDTO(record);
    }

    @PostMapping("/records")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRecord(@RequestBody RecordDTO recordDTO) throws ResourceNotFoundException, ParseException {
        Record record = recordService.mapToEntity(recordDTO);
        record.setClient(clientRepository.findById(record.getClient().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Client is not found for id: " + record.getClient().getId())));

        record.setMaster(masterRepository.findById(record.getMaster().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Master is not found for id: " + record.getMaster().getId())));

        record.setService(serviceRepository.findById(record.getService().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Service is not found for id: " + record.getService().getId())));
        recordRepository.save(record);
    }

    @DeleteMapping("/records/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecord(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {

        try{
            recordRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Record is not found for id: " + id);
        }
    }

    @PutMapping("/records/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRecord(@PathVariable(value = "id") Integer id,
                                                   @RequestBody RecordDTO recordDetails) throws ResourceNotFoundException, ParseException {
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record is not found for id: " + id));

        record.setClient(recordDetails.getClient());
        record.setMaster(recordDetails.getMaster());
        record.setService(recordDetails.getService());
        record.setDateStart(recordDetails.getSubmissionDateStartConverted());
        record.setDateEnd(recordDetails.getSubmissionDateEndConverted());

        recordRepository.save(record);
    }

    @PatchMapping("/records/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRecordPartially(@PathVariable(value = "id") Integer id,
                                                            @RequestBody RecordDTO recordDetails) throws ResourceNotFoundException, ParseException {
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record is not found for id: " + id));

        if (recordDetails.getDateStart() != null)
            record.setDateStart(recordDetails.getSubmissionDateStartConverted());


        if (recordDetails.getDateEnd() != null)
            record.setDateEnd(recordDetails.getSubmissionDateEndConverted());


        if (recordDetails.getClient() != null){
            Integer idClient = recordDetails.getClient().getId();
            Client client = clientRepository.findById(idClient).orElseThrow(
                    () -> new ResourceNotFoundException("Client is not found for id: " + idClient));
            record.setClient(client);
        }
        if (recordDetails.getMaster() != null){
            Integer idMaster = recordDetails.getMaster().getId();
            Master master = masterRepository.findById(idMaster).orElseThrow(
                    () -> new ResourceNotFoundException("Master is not found for id: " + idMaster));
            record.setMaster(master);
        }
        if (recordDetails.getService() != null) {
            Integer idService = recordDetails.getService().getId();
            Service service = serviceRepository.findById(idService).orElseThrow(
                    () -> new ResourceNotFoundException("Service not found for id: " + idService));
            record.setService(service);
        }

        recordRepository.save(record);

    }
}
