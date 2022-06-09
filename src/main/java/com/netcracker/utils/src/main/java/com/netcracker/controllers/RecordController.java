package com.netcracker.controllers;

import com.netcracker.RecordDtoForClientToCreateRecord;
import com.netcracker.dto.RecordDTO;
import com.netcracker.model.Client;
import com.netcracker.model.Favour;
import com.netcracker.model.Master;
import com.netcracker.model.Record;
import com.netcracker.services.ClientService;
import com.netcracker.services.FavourService;
import com.netcracker.services.MasterService;
import com.netcracker.services.RecordService;
import com.netcracker.utils.RecordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/records")
@CrossOrigin(origins = "http://localhost:4200")
public class RecordController {

    @Autowired
    RecordService recordService;

    @Autowired
    ClientService clientService;

    @Autowired
    MasterService masterService;

    @Autowired
    FavourService favourService;

    @Autowired
    private RecordUtil recordUtil;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RecordDTO> getAllRecords() {
        return recordService.getAllRecords().stream().map(recordUtil::mapToDTO).collect(toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecordDTO getRecordById(@PathVariable(value = "id") Integer id) {

        Record record = recordService.getRecordById(id);
        return recordUtil.mapToDTO(record);
    }

    @GetMapping("/master/{master_id}/favour/{favour_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<RecordDtoForClientToCreateRecord> getRecordsOfMasterOnFavour(@PathVariable(value = "master_id") Integer masterId, @PathVariable(value = "favour_id") Integer favourId) {
        return recordService.getRecordsOfMasterOnFavour(masterId, favourId);
    }

    @PostMapping("/create/client/{client_id}/master/{master_id}/favour/{favour_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRecord(@PathVariable(value = "client_id") Integer clientId, @PathVariable(value = "master_id") Integer masterId, @PathVariable(value = "favour_id") Integer favourId, @RequestBody RecordDTO recordDTO) {

        Client client = clientService.getClientById(clientId);
        Master master = masterService.getMasterById(masterId);
        Favour favour = favourService.getFavourById(favourId);
        Record record = recordUtil.mapToEntity(recordDTO);
        record.setClient(client);
        record.setMaster(master);
        record.setFavour(favour);
        Date dateEnd = new Date(record.getDateStart().getTime() + (long) favour.getTime().intValue() * 60 * 1000);
        record.setDateEnd(dateEnd);
        recordService.createRecord(record);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecord(@PathVariable(value = "id") Integer id) {

        recordService.deleteRecord(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRecord(@PathVariable(value = "id") Integer id,
                             @RequestBody RecordDTO recordDTO) {
        Record record = recordUtil.mapToEntity(recordDTO);
        recordService.updateRecord(id, record);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRecordPartially(@PathVariable(value = "id") Integer id,
                                      @RequestBody RecordDTO recordDTO) {
        Record record = recordUtil.mapToEntity(recordDTO);
        recordService.updateRecordPartially(id, record);
    }
}
