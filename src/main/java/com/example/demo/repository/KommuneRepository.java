package com.example.demo.repository;

import com.example.demo.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KommuneRepository extends JpaRepository<Kommune, Integer> {
    List<Kommune> findByRegion_Kode(String regionskode);
}
