package com.netcracker.controller;

import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Category;
import com.netcracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class CategoryController {


    @Autowired
    CategoryRepository repository;


    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category getCategoryById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {

        Category category = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category is not found for id: " + id)
        );

        return category;

    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody Category category){
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
                                           @RequestBody Category categoryDetails) throws ResourceNotFoundException {
        Category category = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category is not found for id: " + id));

        category.setTitle(categoryDetails.getTitle());

        repository.save(category);
    }



}
