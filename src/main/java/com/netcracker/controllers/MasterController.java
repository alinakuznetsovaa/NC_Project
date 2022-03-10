package com.netcracker.controllers;

import com.netcracker.Rec;
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
@RequestMapping("/masters")
@CrossOrigin(origins = "http://localhost:4200")
public class MasterController {

    @Autowired
    MasterService masterService;


    @Autowired
    private MasterUtil masterUtil;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<MasterDTO> getAllMasters() {
        return masterService.getAllMasters().stream().map(masterUtil::mapToDTO).collect(toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MasterDTO getMasterById(@PathVariable(value = "id") Integer id) {

        Master master = masterService.getMasterById(id);
        return masterUtil.mapToDTO(master);
    }


    @GetMapping("/get-all-records-of-master/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Rec> getRecordsOfMaster(@PathVariable(value = "id") Integer id) {
        return masterService.getRecordsOfMaster(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MasterDTO createMaster(@RequestBody MasterDTO masterDTO) {
        Master master = masterUtil.mapToEntity(masterDTO);
        masterService.createMaster(master);
        return masterUtil.mapToDTO(master);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaster(@PathVariable(value = "id") Integer id) {
        masterService.deleteMaster(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMaster(@PathVariable(value = "id") Integer id,
                             @RequestBody MasterDTO masterDTO) {
        Master newMaster = masterUtil.mapToEntity(masterDTO);
        masterService.updateMaster(id, newMaster);

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatedMasterPartially(@PathVariable(value = "id") Integer id,
                                       @RequestBody MasterDTO masterDTO) {
        Master master = masterUtil.mapToEntity(masterDTO);

        masterService.updatedMasterPartially(id, master);

    }
}
