package com.netcracker.controllers;

import com.netcracker.dto.RecordDTO;
import com.netcracker.model.Record;
import com.netcracker.services.RecordService;
import com.netcracker.utils.RecordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/rest")
public class RecordController {

    @Autowired
    RecordService recordService;

    @Autowired
    private RecordUtil recordUtil;


    @GetMapping("/records")
    @ResponseStatus(HttpStatus.OK)
    public List<RecordDTO> getAllRecords() {
        return recordService.getAllRecords().stream().map(recordUtil::mapToDTO).collect(toList());
    }

    @GetMapping("/records/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecordDTO getRecordById(@PathVariable(value = "id") Integer id) {

        Record record = recordService.getRecordById(id);
        return recordUtil.mapToDTO(record);
    }

    @PostMapping("/records")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRecord(@RequestBody RecordDTO recordDTO) {
        Record record = recordUtil.mapToEntity(recordDTO);
        recordService.createRecord(record);
    }

    @DeleteMapping("/records/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecord(@PathVariable(value = "id") Integer id) {

        recordService.deleteRecord(id);
    }

    @PutMapping("/records/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRecord(@PathVariable(value = "id") Integer id,
                             @RequestBody RecordDTO recordDTO) {
        Record record = recordUtil.mapToEntity(recordDTO);
        recordService.updateRecord(id, record);
    }

    @PatchMapping("/records/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRecordPartially(@PathVariable(value = "id") Integer id,
                                      @RequestBody RecordDTO recordDTO) {
        Record record = recordUtil.mapToEntity(recordDTO);
        recordService.updateRecordPartially(id, record);
    }
}
