package com.netcracker.repositories;


import com.netcracker.RecordDtoForClientToCreateRecord;
import com.netcracker.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

    @Query(value = "SELECT favour.title as title, record.date_start as dateStart, record.date_end as dateEnd\n" +
            "FROM favour,record \n" +
            "WHERE \n" +
            "favour.favour_id = :favourId AND \n" +
            "record.master_id = :masterId AND \n" +
            "record.favour_id = :favourId", nativeQuery = true)
    List<RecordDtoForClientToCreateRecord> getRecordsOfMasterOnFavour(Integer masterId, Integer favourId);
}
