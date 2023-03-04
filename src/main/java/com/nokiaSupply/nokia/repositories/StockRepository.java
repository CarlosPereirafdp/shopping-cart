package com.nokiaSupply.nokia.repositories;

import com.nokiaSupply.nokia.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Query("SELECT s FROM Stock s WHERE s.partId=:partID AND s.manufacturerId=:manufacturerID")
    List<Stock> findQuantities(@Param("partID") Integer partID, @Param("manufacturerID") Integer manufacturerID);

    Stock findStockByPartId(Integer id);

    Stock findStockByManufacturerId(Integer id);
}
