package com.netcracker.repositories;

import com.netcracker.FavourDtoToAddFavour;
import com.netcracker.model.Favour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavourRepository extends JpaRepository<Favour, Integer> {
    @Modifying
    @Query(value = "INSERT INTO masters_favours(master_id, favour_id) values (:masterId,:favourId)", nativeQuery = true)
    void setFavoursOfMaster(Integer masterId, Integer favourId);

    @Query(value = "select c.category_id as categoryId , f.favour_id as favourId, c.title as categoryTitle, f.title as favourTitle, f.time as time, f.price as price \n" +
            "from favour as f, masters_favours as mf, category as c \n" +
            "where f.favour_id = mf.favour_id \n" +
            "and f.category_id = c.category_id\n" +
            "and mf.master_id = :id", nativeQuery = true)
    List<FavourDtoToAddFavour> getFavoursOfMaster(Integer id);
}
