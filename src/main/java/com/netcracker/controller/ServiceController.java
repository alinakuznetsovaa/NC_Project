package com.netcracker.controller;

import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Category;
import com.netcracker.model.Service;
import com.netcracker.repository.CategoryRepository;
import com.netcracker.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {

        Service service = serviceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Service not found for id: " + id)
        );

        return ResponseEntity.ok(service);
    }

    @PostMapping("/services")
    public Service createService(@RequestBody Service service) throws ResourceNotFoundException {
        service.setCategory(categoryRepository.findById(service.getCategory().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Category not found for id: " + service.getCategory().getId())));

        return serviceRepository.save(service);
    }

    @DeleteMapping("/services/{id}")
    public String deleteService(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        serviceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Service not found for id: " + id));
        serviceRepository.deleteById(id);

        return "deleted";
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<Service> updateService(@PathVariable(value = "id") Integer id,
                                                   @RequestBody Service serviceDetails) throws ResourceNotFoundException {
        Service service = serviceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Service not found for id: " + id));

        service.setCategory(serviceDetails.getCategory());
        service.setTitle(serviceDetails.getTitle());
        service.setTime(serviceDetails.getTime());


        final Service serviceUpdated = serviceRepository.save(service);

        return ResponseEntity.ok(serviceUpdated);
    }

    @PatchMapping("/services/{id}")
    public ResponseEntity<Service> updateServicePartially(@PathVariable(value = "id") Integer id,
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

        final Service serviceUpdated = serviceRepository.save(service);

        return ResponseEntity.ok(serviceUpdated);


    }
}
