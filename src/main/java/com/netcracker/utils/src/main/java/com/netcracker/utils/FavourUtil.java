package com.netcracker.utils;

import com.netcracker.dto.FavourDTO;
import com.netcracker.model.Favour;
import com.netcracker.repositories.FavourRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FavourUtil {

    private ModelMapper mapper;

    public FavourUtil(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public FavourDTO mapToDTO(Favour favour) {
        return mapper.map(favour, FavourDTO.class);
    }

    public Favour mapToEntity(FavourDTO favourDTO) {
        return mapper.map(favourDTO, Favour.class);
    }
}
