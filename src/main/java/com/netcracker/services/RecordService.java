package com.netcracker.services;

import com.netcracker.dto.ClientDTO;
import com.netcracker.dto.RecordDTO;
import com.netcracker.model.Client;
import com.netcracker.model.Record;
import com.netcracker.repository.ClientRepository;
import com.netcracker.repository.RecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class RecordService {
    private ModelMapper mapper;
    private RecordRepository recordRepository;

    public RecordService(ModelMapper mapper,RecordRepository recordRepository) {
        this.mapper = mapper;
        this.recordRepository = recordRepository;
    }

    public RecordDTO mapToDTO(Record record){
        RecordDTO recordDTO = mapper.map(record, RecordDTO.class);
        recordDTO.setSubmissionDateStart(record.getDateStart());
        recordDTO.setSubmissionDateEnd(record.getDateEnd());
        return recordDTO;
    }

    public Record mapToEntity(RecordDTO recordDTO) throws ParseException {
        Record record = mapper.map(recordDTO, Record.class);
        record.setDateStart(recordDTO.getSubmissionDateStartConverted());
        record.setDateEnd(recordDTO.getSubmissionDateEndConverted());
        return record;
    }
}
