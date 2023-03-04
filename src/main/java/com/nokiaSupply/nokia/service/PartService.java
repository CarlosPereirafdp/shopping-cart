package com.nokiaSupply.nokia.service;

import com.nokiaSupply.nokia.entities.Manufacturer;
import com.nokiaSupply.nokia.entities.Part;
import com.nokiaSupply.nokia.entities.transfer.PartRequest;
import com.nokiaSupply.nokia.repositories.ManufacturerRepository;
import com.nokiaSupply.nokia.repositories.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PartService {
    private final PartRepository partRepository;

    private final ManufacturerRepository manufacturerRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void addPart(PartRequest partRequest) {
        Manufacturer manufacturer = manufacturerRepository.findByName(partRequest.manufaturer());
        if (manufacturer != null) {
            Part part = new Part();
            part.setName(partRequest.name());
            part.setManufacturer(manufacturer);
            part.setPrice(partRequest.price());
            partRepository.save(part);
            System.out.println("Part added with success");
        } else {
            System.out.println("Manufaturer not found");
        }
    }
}
