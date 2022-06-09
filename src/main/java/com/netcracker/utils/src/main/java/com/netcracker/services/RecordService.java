package com.netcracker.services;

import com.netcracker.RecordDtoForClientToCreateRecord;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Client;
import com.netcracker.model.Favour;
import com.netcracker.model.Master;
import com.netcracker.model.Record;
import com.netcracker.repositories.ClientRepository;
import com.netcracker.repositories.FavourRepository;
import com.netcracker.repositories.MasterRepository;
import com.netcracker.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecordService {
    @Autowired
    RecordRepository recordRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    MasterRepository masterRepository;

    @Autowired
    FavourRepository favourRepository;


    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    public Record getRecordById(Integer id) {

        return recordRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record is not found for id: " + id)
        );
    }

    public List<RecordDtoForClientToCreateRecord> getRecordsOfMasterOnFavour(Integer masterId, Integer favourId) {
        return recordRepository.getRecordsOfMasterOnFavour(masterId, favourId);
    }


    public void createRecord(Record record) {
        record.setClient(clientRepository.findById(record.getClient().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Client is not found for id: " + record.getClient().getId())));

        record.setMaster(masterRepository.findById(record.getMaster().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Master is not found for id: " + record.getMaster().getId())));

        record.setFavour(favourRepository.findById(record.getFavour().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Favour is not found for id: " + record.getFavour().getId())));
        recordRepository.save(record);
    }

    public void deleteRecord(Integer id) {

        try {
            recordRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Record is not found for id: " + id);
        }
    }

    public void updateRecord(Integer id, Record newRecord) {
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record is not found for id: " + id));

        record.setClient(newRecord.getClient());
        record.setMaster(newRecord.getMaster());
        record.setFavour(newRecord.getFavour());
        record.setDateStart(newRecord.getDateStart());
        record.setDateEnd(newRecord.getDateEnd());

        recordRepository.save(record);
    }

    public void updateRecordPartially(Integer id, Record newRecord) {
        Record record = recordRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record is not found for id: " + id));

        if (newRecord.getDateStart() != null)
            record.setDateStart(newRecord.getDateStart());


        if (newRecord.getDateEnd() != null)
            record.setDateEnd(newRecord.getDateEnd());


        if (newRecord.getClient() != null) {
            Integer idClient = newRecord.getClient().getId();
            Client client = clientRepository.findById(idClient).orElseThrow(
                    () -> new ResourceNotFoundException("Client is not found for id: " + idClient));
            record.setClient(client);
        }
        if (newRecord.getMaster() != null) {
            Integer idMaster = newRecord.getMaster().getId();
            Master master = masterRepository.findById(idMaster).orElseThrow(
                    () -> new ResourceNotFoundException("Master is not found for id: " + idMaster));
            record.setMaster(master);
        }
        if (newRecord.getFavour() != null) {
            Integer idFavour = newRecord.getFavour().getId();
            Favour favour = favourRepository.findById(idFavour).orElseThrow(
                    () -> new ResourceNotFoundException("Favour not found for id: " + idFavour));
            record.setFavour(favour);
        }

        recordRepository.save(record);

    }
}
