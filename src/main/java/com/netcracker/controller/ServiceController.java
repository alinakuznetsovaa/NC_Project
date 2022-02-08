package com.netcracker.controller;

import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Category;
import com.netcracker.model.Service;
import com.netcracker.repository.CategoryRepository;
import com.netcracker.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ServiceController {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping("/services")
    @ResponseStatus(HttpStatus.OK)
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/services/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Service getServiceById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {

        Service service = serviceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Service not found for id: " + id)
        );

        return service;
    }

    @PostMapping("/services")
    @ResponseStatus(HttpStatus.CREATED)
    public void createService(@RequestBody Service service) throws ResourceNotFoundException {
        service.setCategory(categoryRepository.findById(service.getCategory().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Category not found for id: " + service.getCategory().getId())));

        serviceRepository.save(service);
    }


    @DeleteMapping("/services/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {

        try{
            serviceRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Service is not found for id: " + id);
        }
    }

    @PutMapping("/services/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateService(@PathVariable(value = "id") Integer id,
                                                   @RequestBody Service serviceDetails) throws ResourceNotFoundException {
        Service service = serviceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Service not found for id: " + id));

        service.setCategory(serviceDetails.getCategory());
        service.setTitle(serviceDetails.getTitle());
        service.setTime(serviceDetails.getTime());


        serviceRepository.save(service);

    }

    @PatchMapping("/services/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateServicePartially(@PathVariable(value = "id") Integer id,
                                                            @RequestBody Service serviceDetails) throws ResourceNotFoundException {
        Service service = serviceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Service not found for id: " + id));

        if (serviceDetails.getCategory() != null) {
            Integer idCategory = serviceDetails.getCategory().getId();
            Category category = categoryRepository.findById(idCategory).orElseThrow(
                    () -> new ResourceNotFoundException("Category not found for id: " + idCategory));
            service.setCategory(category);
        }

        if (serviceDetails.getTitle() != null)
            service.setTitle(serviceDetails.getTitle());

        if (serviceDetails.getTime() != null)
            service.setTime(serviceDetails.getTime());

        serviceRepository.save(service);


    }
}
