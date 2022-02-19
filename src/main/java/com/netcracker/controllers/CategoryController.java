package com.netcracker.controllers;

import com.netcracker.dto.CategoryDTO;
import com.netcracker.model.Category;
import com.netcracker.services.CategoryService;
import com.netcracker.utils.CategoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/rest")
public class CategoryController {


    @Autowired
    CategoryService categoryService;


    @Autowired
    private CategoryUtil categoryUtil;


    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories().stream().map(categoryUtil::mapToDTO).collect(toList());
    }

    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById(@PathVariable(value = "id") Integer id) {

        Category category = categoryService.getCategoryById(id);
        return categoryUtil.mapToDTO(category);

    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryUtil.mapToEntity(categoryDTO);
        categoryService.createCategory(category);
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable(value = "id") Integer id) {
        categoryService.deleteCategory(id);
    }

    @PutMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCategory(@PathVariable(value = "id") Integer id,
                               @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryUtil.mapToEntity(categoryDTO);

        categoryService.updateCategory(id, category);
    }


}
