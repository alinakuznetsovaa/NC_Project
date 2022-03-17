package com.netcracker.repositories;

import com.netcracker.Rec;
import com.netcracker.model.Client;
import com.netcracker.model.Master;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Convert;
import java.sql.Date;
import java.time.LocalDateTime;
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
    List<Rec> getRecordsOfMaster(Integer id);

    @Query(value = "select * from master as m \n" +
            "  where m.email = :email \n" +
            "  and m.password = :password ", nativeQuery = true)
    Master getMasterOnLogin(String email, String password);

   
    @Query(value = "select cd.date from categories_dates as cd \n" +
            "  where cd.category_id = :categoryId \n" +
            "  and cd.master_id = :masterId ", nativeQuery = true)
    List<LocalDateTime> getFreeDates(Integer categoryId, Integer masterId);

    @Modifying
    @Query(value = "INSERT INTO categories_masters(category_id,master_id,date) values (:categoryId,:masterId,:date)", nativeQuery = true)
    void setFreeDatesOfMaster(Integer categoryId, Integer masterId, LocalDateTime date);


}
