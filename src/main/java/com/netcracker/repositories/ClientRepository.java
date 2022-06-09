package com.netcracker.repositories;

import com.netcracker.FavourDtoForClient;
import com.netcracker.RecordDtoForClient;
import com.netcracker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT record.record_id as recordId, master.first_name as firstName,master.last_name as lastName, \n" +
            "favour.title as title, favour.price as price, record.date_start as dateStart, record.date_end as dateEnd\n" +
            "FROM master,favour,record \n" +
            "WHERE \n" +
            "record.client_id = :id AND \n" +
            "record.master_id = master.master_id AND \n" +
            "record.favour_id = favour.favour_id", nativeQuery = true)
    List<RecordDtoForClient> getRecordsOfClient(Integer id);

    @Query(value = "select * from client as c \n" +
            "  where c.email = :email \n" +
            "  and c.password = :password ", nativeQuery = true)
    Client getClientOnLogin(String email, String password);

    @Query(value = "SELECT f.favour_id as favourId, m.master_id as masterId, m.first_name as masterFirstName, m.last_name as masterLastName, c.title as categoryTitle, f.title as favourTitle, f.time as time, f.price as price \n" +
            "  FROM category as c, favour as f, master as m, masters_favours as mf \n" +
            "  WHERE c.category_id = :categoryId \n" +
            "  AND f.category_id = :categoryId \n" +
            "  AND mf.favour_id = f.favour_id \n" +
            "  and m.master_id = mf.master_id", nativeQuery = true)
    List<FavourDtoForClient> getFavoursOfCategory(Integer categoryId);
}
