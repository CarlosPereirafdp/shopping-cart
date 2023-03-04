package com.nokiaSupply.nokia.service;

import com.nokiaSupply.nokia.entities.Manufacturer;
import com.nokiaSupply.nokia.entities.Part;
import com.nokiaSupply.nokia.entities.transfer.ManufaturerRequest;
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
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final StockRepository stockRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    public void addManufaturer(ManufaturerRequest manufaturerRequest) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufaturerRequest.name());

        manufacturerRepository.save(manufacturer);
        System.out.println("Manufaturer added with success");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeManufacturer(String name) {
        Manufacturer manufacturer = manufacturerRepository.findByName(name);
        if(stockRepository.findStockByManufacturerId(manufacturer.getId()) != null){
            System.out.println("You cant delete this Manufaturer because they have parts in Stock that are not solded yet");
        }else {
            manufacturerRepository.delete(manufacturer);
            System.out.println("Manufaturer deleted with success");
        }
    }
}
