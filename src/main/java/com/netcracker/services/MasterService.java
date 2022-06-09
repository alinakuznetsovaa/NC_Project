package com.netcracker.services;

import com.netcracker.Login;
import com.netcracker.RecordDtoForClient;
import com.netcracker.RecordDtoForMaster;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Master;
import com.netcracker.repositories.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<RecordDtoForMaster> getRecordsOfMaster(Integer id) {
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

        masterRepository.save(master);

    }
}
