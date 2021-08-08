package com.volha.listcafe.repository;

import com.volha.listcafe.dto.CafeDto;
import com.volha.listcafe.model.Cafe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Created on 03.08.2021
 */
@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
    @Query("select c from Cafe c join fetch c.address where (c.name = :name or :name is null or :name ='')" )
    List<Cafe> findAllCafes(Pageable page, @Param("name") String name);
}
