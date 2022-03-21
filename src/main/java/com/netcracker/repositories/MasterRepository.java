package com.netcracker.repositories;

import com.netcracker.RecordDtoForClient;
import com.netcracker.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterRepository extends JpaRepository<Master, Integer> {
    @Query(value = "SELECT client.first_name as firstName,client.last_name as lastName,client.email as address, \n" +
            "favour.title as title, record.date_start as dateStart, record.date_end as dateEnd \n" +
            "FROM client,favour,record \n" +
            "WHERE \n" +
            "record.master_id = :id AND \n" +
            "record.client_id = client.client_id AND \n" +
            "record.favour_id = favour.favour_id", nativeQuery = true)
    List<RecordDtoForClient> getRecordsOfMaster(Integer id);

    @Query(value = "select * from master as m \n" +
            "  where m.email = :email \n" +
            "  and m.password = :password ", nativeQuery = true)
    Master getMasterOnLogin(String email, String password);
}
