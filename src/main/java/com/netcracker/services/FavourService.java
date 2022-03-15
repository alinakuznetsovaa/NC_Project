package com.netcracker.services;

import com.netcracker.Fav;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Category;
import com.netcracker.model.Favour;
import com.netcracker.repositories.CategoryRepository;
import com.netcracker.repositories.FavourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class FavourService {

    @Autowired
    FavourRepository favourRepository;


    @Autowired
    CategoryRepository categoryRepository;

    public List<Favour> getAllFavours() {
        return favourRepository.findAll();
    }

    public Favour getFavourById(Integer id) {

        return favourRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Favour not found for id: " + id)
        );
    }

    public List<Fav> getFavoursOfMaster(Integer id) {
        return favourRepository.getFavoursOfMaster(id);
    }

    @Transactional
    public void createFavour(Integer masterId, Favour favour) {
        favour.setCategory(categoryRepository.findById(favour.getCategory().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Category not found for id: " + favour.getCategory().getId())));

        favourRepository.save(favour);
        favourRepository.setFavoursOfMaster(masterId, favour.getId());
    }

    public void deleteFavour(Integer id) {
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
