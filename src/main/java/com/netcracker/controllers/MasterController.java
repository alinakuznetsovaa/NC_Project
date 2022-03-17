package com.netcracker.controllers;

import com.netcracker.Login;
import com.netcracker.Rec;
import com.netcracker.dto.ClientDTO;
import com.netcracker.dto.FavourDTO;
import com.netcracker.dto.MasterDTO;
import com.netcracker.model.Category;
import com.netcracker.model.Client;
import com.netcracker.model.Favour;
import com.netcracker.model.Master;
import com.netcracker.services.CategoryService;
import com.netcracker.services.MasterService;
import com.netcracker.utils.MasterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/masters")
@CrossOrigin(origins = "http://localhost:4200")
public class MasterController {

    @Autowired
    MasterService masterService;

    @Autowired
    CategoryService categoryService;


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

    @GetMapping("/get-free-dates/category/{category_id}/master/{master_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<LocalDateTime> getFreeDates(@PathVariable(value = "category_id")Integer categoryId, @PathVariable(value = "master_id") Integer masterId) {

        List<LocalDateTime> dates = masterService.getFreeDates(categoryId,masterId);
        return dates;
    }

    @PostMapping("/get-master-on-login")
    @ResponseStatus(HttpStatus.OK)
    public MasterDTO getMasterOnLogin(@RequestBody Login login) {
        Master master = masterService.getMasterOnLogin(login);
        return masterUtil.mapToDTO(master);
    }

    @PostMapping("/category/{category_id}/master/{master_id}/addfreetime")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDate(@PathVariable(value = "category_id") Integer categoryId, @PathVariable(value = "master_id") Integer masterId, @RequestBody LocalDateTime date) {
       masterService.setFreeDatesOfMaster(categoryId,masterId,date);
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
