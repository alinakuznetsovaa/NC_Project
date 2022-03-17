package com.netcracker.services;

import com.netcracker.Login;
import com.netcracker.Rec;
import com.netcracker.dto.MasterDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Client;
import com.netcracker.model.Master;
import com.netcracker.repositories.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class MasterService {

    @Autowired
    MasterRepository masterRepository;

    public List<Master> getAllMasters() {
        return masterRepository.findAll();
    }

    public Master getMasterById(Integer id) {

        return masterRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master is not found for id: " + id)
        );
    }

    public Master getMasterOnLogin(Login login) {

        return masterRepository.getMasterOnLogin(login.getEmail(), login.getPassword());
    }

    public List<LocalDateTime> getFreeDates(Integer masterId, Integer categoryId) {

        return masterRepository.getFreeDates(categoryId,masterId);
    }

    @Transactional
    public void setFreeDatesOfMaster(Integer categoryId, Integer masterId, LocalDateTime date){
        masterRepository.setFreeDatesOfMaster(categoryId, masterId, date);
    }

    public List<Rec> getRecordsOfMaster(Integer id) {
        return masterRepository.getRecordsOfMaster(id);
    }

    public void createMaster(Master master) {
        masterRepository.save(master);
    }

    public void deleteMaster(Integer id) {
        try {
            masterRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Master is not found for id: " + id);
        }
    }

    public void updateMaster(Integer id, Master newMaster) {
        Master master = masterRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master is not found for id: " + id)
        );

        master.setFirstName(newMaster.getFirstName());
        master.setLastName(newMaster.getLastName());
        master.setEmail(newMaster.getEmail());
        master.setAddress(newMaster.getAddress());

        masterRepository.save(master);

    }

    public void updatedMasterPartially(Integer id, Master newMaster) {
        Master master = masterRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Master is not found for id: " + id));

        if (newMaster.getFirstName() != null)
            master.setFirstName(newMaster.getFirstName());

        if (newMaster.getLastName() != null)
            master.setLastName(newMaster.getLastName());

        if (newMaster.getEmail() != null)
            master.setEmail(newMaster.getEmail());

        if (newMaster.getAddress() != null)
            master.setAddress((newMaster.getAddress()));

        masterRepository.save(master);

    }
}
