package com.netcracker.services;

import com.netcracker.dto.CategoryDTO;
import com.netcracker.dto.ClientDTO;
import com.netcracker.model.Category;
import com.netcracker.model.Client;
import com.netcracker.repository.CategoryRepository;
import com.netcracker.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private ModelMapper mapper;
    private CategoryRepository categoryRepository;

    public CategoryService(ModelMapper mapper, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO mapToDTO(Category category){
        CategoryDTO categoryDTO = mapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    public Category mapToEntity(CategoryDTO categoryDTO){
        Category category = mapper.map(categoryDTO, Category.class);
        return category;
    }
}
