package com.netcracker.controller;

import com.netcracker.dto.CategoryDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Category;
import com.netcracker.repository.CategoryRepository;
import com.netcracker.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/rest")
public class CategoryController {


    @Autowired
    CategoryRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getAllCategories() {
        return repository.findAll().stream().map(categoryService::mapToDTO).collect(toList());
    }

    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {

        Category category = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category is not found for id: " + id)
        );

        return categoryService.mapToDTO(category);

    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = categoryService.mapToEntity(categoryDTO);
        repository.save(category);
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
       try {
           repository.deleteById(id);
       }catch (EmptyResultDataAccessException e){
           throw new ResourceNotFoundException("Category is not found for id: " + id);
       }

    }

    @PutMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCategory(@PathVariable(value = "id") Integer id,
                                           @RequestBody CategoryDTO categoryDetails) throws ResourceNotFoundException {
        Category category = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category is not found for id: " + id));

        category.setTitle(categoryDetails.getTitle());

        repository.save(category);
    }



}
