package com.netcracker.services;

import com.netcracker.dto.FavourDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Category;
import com.netcracker.model.Favour;
import com.netcracker.repositories.CategoryRepository;
import com.netcracker.repositories.FavourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class FavourService {

    @Autowired
    FavourRepository favourRepository;


    @Autowired
    CategoryRepository categoryRepository;

    public List<Favour> getAllServices() {
        return favourRepository.findAll();
    }

    public Favour getServiceById(Integer id) {

        return favourRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Favour not found for id: " + id)
        );
    }


    public void createService(Favour favour) {
        favour.setCategory(categoryRepository.findById(favour.getCategory().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Category not found for id: " + favour.getCategory().getId())));

        favourRepository.save(favour);
    }

    public void deleteService(Integer id) {
        try {
            favourRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Favour is not found for id: " + id);
        }
    }

    public void updateFavour(Integer id, Favour newFavour) {
        Favour favour = favourRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Favour not found for id: " + id));

        favour.setCategory(newFavour.getCategory());
        favour.setTitle(newFavour.getTitle());
        favour.setTime(newFavour.getTime());


        favourRepository.save(favour);

    }


    public void updateFavourPartially(Integer id, Favour newFavour) {
        Favour favour = favourRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Service not found for id: " + id));

        if (newFavour.getCategory() != null) {
            Integer idCategory = newFavour.getCategory().getId();
            Category category = categoryRepository.findById(idCategory).orElseThrow(
                    () -> new ResourceNotFoundException("Category not found for id: " + idCategory));
            favour.setCategory(category);
        }

        if (newFavour.getTitle() != null)
            favour.setTitle(newFavour.getTitle());

        if (newFavour.getTime() != null)
            favour.setTime(newFavour.getTime());

        favourRepository.save(favour);


    }
}
