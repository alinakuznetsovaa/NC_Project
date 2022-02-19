package com.netcracker.repositories;

import com.netcracker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT master.first_name,master.last_name,master.adress, \n" +
            "favour.title, record.date_start, record.date_end \n" +
            "FROM master,favour,record \n" +
            "WHERE \n" +
            "record.client_id = :id AND \n" +
            "record.master_id = master.master_id AND \n" +
            "record.favour_id = favour.favour_id", nativeQuery = true)
    List<String> getRecordsOfClient(Integer id);
}
