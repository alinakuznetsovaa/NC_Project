package com.netcracker.utils;

import com.netcracker.dto.CategoryDTO;
import com.netcracker.model.Category;
import com.netcracker.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryUtil {
    private ModelMapper mapper;

    public CategoryUtil(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CategoryDTO mapToDTO(Category category) {
        return mapper.map(category, CategoryDTO.class);
    }

    public Category mapToEntity(CategoryDTO categoryDTO) {
        return mapper.map(categoryDTO, Category.class);
    }
}
