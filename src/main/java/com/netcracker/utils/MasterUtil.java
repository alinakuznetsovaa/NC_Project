package com.netcracker.utils;

import com.netcracker.dto.MasterDTO;
import com.netcracker.model.Master;
import com.netcracker.repositories.MasterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MasterUtil {
    private ModelMapper mapper;
    private MasterRepository masterRepository;

    public MasterUtil(ModelMapper mapper, MasterRepository masterRepository) {
        this.mapper = mapper;
        this.masterRepository = masterRepository;
    }

    public MasterDTO mapToDTO(Master master) {
        return mapper.map(master, MasterDTO.class);
    }

    public Master mapToEntity(MasterDTO masterDTO) {
        return mapper.map(masterDTO, Master.class);
    }
}
