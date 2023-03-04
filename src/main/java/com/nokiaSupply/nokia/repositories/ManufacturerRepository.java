package com.nokiaSupply.nokia.repositories;

import com.nokiaSupply.nokia.entities.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

    @Query("SELECT m FROM Manufacturer m WHERE m.name=:name")
    Manufacturer findByName(@Param("name") String name);
}
