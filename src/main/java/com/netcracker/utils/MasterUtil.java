package com.netcracker.utils;

import com.netcracker.dto.MasterDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Master;
import com.netcracker.repositories.MasterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MasterUtil {
    private ModelMapper mapper;

    public MasterUtil(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public MasterDTO mapToDTO(Master master) {
        try {
            return mapper.map(master, MasterDTO.class);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Master is not found");
        }
    }

    public Master mapToEntity(MasterDTO masterDTO) {
        return mapper.map(masterDTO, Master.class);
    }
}
