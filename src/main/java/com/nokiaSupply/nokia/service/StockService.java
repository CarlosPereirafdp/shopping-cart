package com.nokiaSupply.nokia.service;

import com.nokiaSupply.nokia.entities.Manufacturer;
import com.nokiaSupply.nokia.entities.Part;
import com.nokiaSupply.nokia.entities.Stock;
import com.nokiaSupply.nokia.repositories.ManufacturerRepository;
import com.nokiaSupply.nokia.repositories.PartRepository;
import com.nokiaSupply.nokia.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final PartRepository partRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addQuantity(String partName, String manufacturerName, Integer quantity) {
        Manufacturer manufacturer = manufacturerRepository.findByName(manufacturerName);
        Part part = partRepository.findByName(partName);
        if (manufacturer != null && part != null) {
            Stock stock = new Stock();
            stock.setQuantity(quantity);
            stock.setManufacturerId(manufacturer.getId());
            stock.setPartId(part.getId());
            stockRepository.saveAndFlush(stock);
            System.out.println("Quantity add with success");
        } else {
            System.out.println("Manufaturer or part not found");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void listQuantities(String partName, String manufacturerName) {
        Manufacturer manufacturer = manufacturerRepository.findByName(manufacturerName);
        Part part = partRepository.findByName(partName);
        if (manufacturer != null && part != null) {
            List<Stock> stockList = stockRepository.findQuantities(part.getId(), manufacturer.getId());
            if (!stockList.isEmpty()) {
                for (Stock q : stockList) {
                    Part p = partRepository.findById(q.getPartId()).orElse(null);
                    Manufacturer m = manufacturerRepository.findById(Math.toIntExact(q.getManufacturerId())).orElse(null);

                    System.out.println(String.format("Part name: %s", p.getName()));
                    System.out.println(String.format("Manufacturer name: %s", m.getName()));
                    System.out.println(String.format("Price: %,.2f", p.getPrice()));
                    System.out.println(String.format("Quantity: %d", q.getQuantity()));
                }
            } else {
                System.out.println("Quantity not found");
            }
        } else {
            System.out.println("Manufaturer or part not found");
        }
    }
}
