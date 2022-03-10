package com.netcracker.controllers;

import com.netcracker.dto.FavourDTO;
import com.netcracker.model.Favour;
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
    private FavourUtil favourUtil;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<FavourDTO> getAllFavours() {
        return favourService.getAllServices().stream().map(favourUtil::mapToDTO).collect(toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FavourDTO getFavourById(@PathVariable(value = "id") Integer id) {

        Favour favour = favourService.getServiceById(id);

        return favourUtil.mapToDTO(favour);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public FavourDTO createFavour(@RequestBody FavourDTO favourDTO) {
        Favour favour = favourUtil.mapToEntity(favourDTO);
        favourService.createService(favour);
        return favourUtil.mapToDTO(favour);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFavour(@PathVariable(value = "id") Integer id) {

        favourService.deleteService(id);
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
