package com.netcracker.services;

import com.netcracker.dto.ClientDTO;
import com.netcracker.dto.MasterDTO;
import com.netcracker.model.Client;
import com.netcracker.model.Master;
import com.netcracker.repository.ClientRepository;
import com.netcracker.repository.MasterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MasterService {
    private ModelMapper mapper;
    private MasterRepository masterRepository;

    public MasterService(ModelMapper mapper, MasterRepository masterRepository) {
        this.mapper = mapper;
        this.masterRepository = masterRepository;
    }

    public MasterDTO mapToDTO(Master master){
        MasterDTO masterDTO = mapper.map(master, MasterDTO.class);
        return masterDTO;
    }

    public Master mapToEntity(MasterDTO masterDTO){
        Master master = mapper.map(masterDTO, Master.class);
        return master;
    }
}
