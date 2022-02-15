package com.netcracker.services;

import com.netcracker.dto.ServiceDTO;
import com.netcracker.model.Client;
import com.netcracker.repository.ServiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {

    private ModelMapper mapper;
    private ServiceRepository serviceRepository;

    public ServiceService(ModelMapper mapper, ServiceRepository serviceRepository) {
        this.mapper = mapper;
        this.serviceRepository = serviceRepository;
    }

    public ServiceDTO mapToDTO(com.netcracker.model.Service service){
        ServiceDTO serviceDTO = mapper.map(service, ServiceDTO.class);
        return serviceDTO;
    }

    public com.netcracker.model.Service mapToEntity(ServiceDTO serviceDTO){
        com.netcracker.model.Service service = mapper.map(serviceDTO, com.netcracker.model.Service.class);
        return service;
    }
}
