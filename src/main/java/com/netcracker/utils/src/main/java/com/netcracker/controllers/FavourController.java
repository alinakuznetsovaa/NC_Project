package com.netcracker.controllers;

import com.netcracker.FavourDtoToAddFavour;
import com.netcracker.dto.FavourDTO;
import com.netcracker.model.Category;
import com.netcracker.model.Favour;
import com.netcracker.services.CategoryService;
import com.netcracker.services.FavourService;
import com.netcracker.utils.FavourUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/favours")
@CrossOrigin(origins = "http://localhost:4200")
public class FavourController {

    @Autowired
    FavourService favourService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private FavourUtil favourUtil;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<FavourDTO> getAllFavours() {
        return favourService.getAllFavours().stream().map(favourUtil::mapToDTO).collect(toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FavourDTO getFavourById(@PathVariable(value = "id") Integer id) {

        Favour favour = favourService.getFavourById(id);


        return favourUtil.mapToDTO(favour);
    }

    @GetMapping("/{master_id}/get-favours-of-master")
    @ResponseStatus(HttpStatus.OK)
    public List<FavourDtoToAddFavour> getFavoursOfMaster(@PathVariable(value = "master_id") Integer id) {


        return favourService.getFavoursOfMaster(id);
    }

    @PostMapping("/master/{master_id}/category/{category_id}/addfavours")
    @ResponseStatus(HttpStatus.CREATED)
    public FavourDTO createFavour(@PathVariable(value = "master_id") Integer masterId, @PathVariable(value = "category_id") Integer categoryId, @RequestBody FavourDTO favourDTO) {
        Category category = categoryService.getCategoryById(categoryId);
        Favour favour = favourUtil.mapToEntity(favourDTO);
        favour.setCategory(category);
        favourService.createFavour(masterId, favour);
        return favourUtil.mapToDTO(favour);

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFavour(@PathVariable(value = "id") Integer id) {

        favourService.deleteFavour(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateFavour(@PathVariable(value = "id") Integer id,
                             @RequestBody FavourDTO favourDTO) {
        Favour newFavour = favourUtil.mapToEntity(favourDTO);
        favourService.updateFavour(id, newFavour);

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateFavourPartially(@PathVariable(value = "id") Integer id,
                                      @RequestBody FavourDTO favourDTO) {
        Favour newFavour = favourUtil.mapToEntity(favourDTO);
        favourService.updateFavourPartially(id, newFavour);


    }
}
