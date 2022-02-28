package com.netcracker.repositories;

import com.netcracker.model.Favour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavourRepository extends JpaRepository<Favour, Integer> {
}
