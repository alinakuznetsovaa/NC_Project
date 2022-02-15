package com.netcracker.repository;

import com.netcracker.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterRepository extends JpaRepository<Master, Integer> {
    @Query(value = "SELECT client.first_name,client.last_name,client.email, \n" +
            "service.title, record.date_start, record.date_end \n" +
            "FROM client,service,record \n" +
            "WHERE \n" +
            "record.master_id = :id AND \n" +
            "record.client_id = client.client_id and\n" +
            "record.service_id = service.service_id" , nativeQuery = true)
    List<List<String>> getRecordsOfMaster(Integer id);
}
