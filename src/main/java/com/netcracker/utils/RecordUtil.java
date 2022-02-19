package com.netcracker.utils;

import com.netcracker.dto.RecordDTO;
import com.netcracker.model.Record;
import com.netcracker.repositories.RecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class RecordUtil {
    private ModelMapper mapper;
    private RecordRepository recordRepository;

    public RecordUtil(ModelMapper mapper, RecordRepository recordRepository) {
        this.mapper = mapper;
        this.recordRepository = recordRepository;
    }

    public RecordDTO mapToDTO(Record record) {
        RecordDTO recordDTO = mapper.map(record, RecordDTO.class);
        recordDTO.setSubmissionDateStart(record.getDateStart());
        recordDTO.setSubmissionDateEnd(record.getDateEnd());
        return recordDTO;
    }

    public Record mapToEntity(RecordDTO recordDTO) {
        Record record = mapper.map(recordDTO, Record.class);

        if (recordDTO.getDateStart() != null) {
            try {
                record.setDateStart(recordDTO.getSubmissionDateStartConverted());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (record.getDateEnd() != null) {
            try {
                record.setDateEnd(recordDTO.getSubmissionDateEndConverted());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return record;
    }
}
