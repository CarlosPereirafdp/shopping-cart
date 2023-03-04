package com.nokiaSupply.nokia.repositories;

import com.nokiaSupply.nokia.entities.Manufacturer;
import com.nokiaSupply.nokia.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PartRepository extends JpaRepository<Part, Integer> {

    @Query("SELECT p FROM Part p WHERE p.name=:name")
    Part findByName(@Param("name") String name);
}
