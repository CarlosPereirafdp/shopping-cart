package com.nokiaSupply.nokia;

import com.nokiaSupply.nokia.entities.Manufacturer;
import com.nokiaSupply.nokia.entities.Stock;
import com.nokiaSupply.nokia.entities.transfer.ManufaturerRequest;
import com.nokiaSupply.nokia.repositories.ManufacturerRepository;
import com.nokiaSupply.nokia.repositories.StockRepository;
import com.nokiaSupply.nokia.service.ManufacturerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class ManufacturerServiceTest {

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ManufacturerService manufacturerService;

    @Test
    public void add_manufacturer_should_save_to_repository() {
        String manufacturerName = "Tesla";

        ManufaturerRequest manufaturerRequest = new ManufaturerRequest(manufacturerName);

        manufacturerService.addManufaturer(manufaturerRequest);


        verify(manufacturerRepository).save(any(Manufacturer.class));
    }

    @Test
    public void remove_manufacturer_should_delete_manufacturer_from_repository() {
        String manufacturerName = "Tesla";

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1);
        manufacturer.setName(manufacturerName);

        when(manufacturerRepository.findByName(manufacturerName)).thenReturn(manufacturer);
        when(stockRepository.findStockByManufacturerId(manufacturer.getId())).thenReturn(null);

        manufacturerService.removeManufacturer(manufacturerName);

        verify(manufacturerRepository).delete(manufacturer);
    }

    @Test
    public void remove_manufacturer_should_not_delete_Manufacturer_With_unsold_Stock() {
        String manufacturerName = "Tesla";

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1);
        manufacturer.setName(manufacturerName);

        Stock stock = new Stock();
        stock.setQuantity(10);
        stock.setManufacturerId(manufacturer.getId());

        when(manufacturerRepository.findByName(manufacturerName)).thenReturn(manufacturer);
        when(stockRepository.findStockByManufacturerId(manufacturer.getId())).thenReturn(stock);

        manufacturerService.removeManufacturer(manufacturerName);


        verify(manufacturerRepository, never()).delete(manufacturer);
        verify(stockRepository, never()).delete(stock);
    }
}






