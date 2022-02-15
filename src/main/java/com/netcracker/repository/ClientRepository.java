package com.netcracker.repository;

import com.netcracker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT master.first_name,master.last_name,master.adress, \n" +
            "service.title, record.date_start, record.date_end \n" +
            "FROM master,service,record \n" +
            "WHERE \n" +
            "record.client_id = :id AND \n" +
            "record.master_id = master.master_id and\n" +
            "record.service_id = service.service_id" , nativeQuery = true)
    List<List<String>> getRecordsOfClient(Integer id);
}
