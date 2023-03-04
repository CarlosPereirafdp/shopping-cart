package com.nokiaSupply.nokia;

import com.nokiaSupply.nokia.entities.Manufacturer;
import com.nokiaSupply.nokia.entities.Part;
import com.nokiaSupply.nokia.entities.transfer.PartRequest;
import com.nokiaSupply.nokia.repositories.ManufacturerRepository;
import com.nokiaSupply.nokia.repositories.PartRepository;
import com.nokiaSupply.nokia.service.PartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PartServiceTest {

    @Mock
    private PartRepository partRepository;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @InjectMocks
    private PartService partService;

    @Test
    public void addPart_shouldAddPartWhenManufacturerExists() {

        String manufacturerName = "Tesla";
        String partName = "Widget";
        double price = 10.00;

        PartRequest partRequest = new PartRequest(partName, manufacturerName, price);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufacturerName);

        when(manufacturerRepository.findByName(manufacturerName)).thenReturn(manufacturer);


        partService.addPart(partRequest);


        verify(manufacturerRepository).findByName(manufacturerName);
        verify(partRepository).save(any(Part.class));
    }

    @Test
    public void addPart_shouldNotAddPartWhenManufacturerDoesNotExist() {

        String manufacturerName = "Tesla";
        String partName = "Widget";
        double price = 10.00;

        PartRequest partRequest = new PartRequest(partName, manufacturerName, price);

        when(manufacturerRepository.findByName(manufacturerName)).thenReturn(null);

        partService.addPart(partRequest);

        verify(manufacturerRepository).findByName(manufacturerName);
        verify(partRepository, never()).save(any(Part.class));
    }
}