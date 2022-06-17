package com.netcracker.utils;

import com.netcracker.dto.RecordDTO;
import com.netcracker.model.Record;
import com.netcracker.repositories.RecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Component
public class RecordUtil {
    private ModelMapper mapper;

    public RecordUtil(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public RecordDTO mapToDTO(Record record) {
        RecordDTO recordDTO = mapper.map(record, RecordDTO.class);
        recordDTO.setSubmissionDateStart(record.getDateStart());
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

        return record;
    }
}
