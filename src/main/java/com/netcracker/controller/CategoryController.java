package com.netcracker.controller;


import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Category;
import com.netcracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class CategoryController {


    @Autowired
    CategoryRepository repository;

//    @Autowired
//    MainService mainService;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {

        Category category = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found for id: " + id)
        );

        return ResponseEntity.ok(category);
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category){
        return repository.save(category);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not found for id: " + id));
        repository.deleteById(id);

        return ResponseEntity.ok("deleted");
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Integer id,
                                           @RequestBody Category categoryDetails) throws ResourceNotFoundException {
        Category category = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category not found for id: " + id));

        category.setTitle(categoryDetails.getTitle());

        final Category updatedCategory = repository.save(category);

        return ResponseEntity.ok(updatedCategory);
    }



}
