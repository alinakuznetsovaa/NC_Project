package com.netcracker.repositories;

import com.netcracker.RecordDtoForClient;
import com.netcracker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT record.record_id as recordId, master.first_name as firstName,master.last_name as lastName,master.address as address, \n" +
            "favour.title as title, record.date_start as dateStart, record.date_end as dateEnd\n" +
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
}
