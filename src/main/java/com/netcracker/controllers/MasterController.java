package com.netcracker.controllers;

import com.netcracker.dto.MasterDTO;
import com.netcracker.model.Master;
import com.netcracker.services.MasterService;
import com.netcracker.utils.MasterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/rest")
public class MasterController {

    @Autowired
    MasterService masterService;


    @Autowired
    private MasterUtil masterUtil;

    @GetMapping("/masters")
    @ResponseStatus(HttpStatus.OK)
    public List<MasterDTO> getAllMasters() {
        return masterService.getAllMasters().stream().map(masterUtil::mapToDTO).collect(toList());
    }

    @GetMapping("/masters/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MasterDTO getMasterById(@PathVariable(value = "id") Integer id) {

        Master master = masterService.getMasterById(id);
        return masterUtil.mapToDTO(master);
    }


    @GetMapping("/masters/get-records-of-master/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getRecordsOfMaster(@PathVariable(value = "id") Integer id) {
        return masterService.getRecordsOfMaster(id);
    }

    @PostMapping("/masters")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMaster(@RequestBody MasterDTO masterDTO) {
        Master master = masterUtil.mapToEntity(masterDTO);
        masterService.createMaster(master);
    }


    @DeleteMapping("/masters/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaster(@PathVariable(value = "id") Integer id) {
        masterService.deleteMaster(id);
    }

    @PutMapping("/masters/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMaster(@PathVariable(value = "id") Integer id,
                             @RequestBody MasterDTO masterDTO) {
        Master newMaster = masterUtil.mapToEntity(masterDTO);
        masterService.updateMaster(id, newMaster);

    }

    @PatchMapping("/masters/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatedMasterPartially(@PathVariable(value = "id") Integer id,
                                       @RequestBody MasterDTO masterDTO) {
        Master master = masterUtil.mapToEntity(masterDTO);

        masterService.updatedMasterPartially(id, master);

    }
}
