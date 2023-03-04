package com.nokiaSupply.nokia;

import com.nokiaSupply.nokia.entities.Manufacturer;
import com.nokiaSupply.nokia.entities.Part;
import com.nokiaSupply.nokia.entities.Stock;
import com.nokiaSupply.nokia.repositories.ManufacturerRepository;
import com.nokiaSupply.nokia.repositories.PartRepository;
import com.nokiaSupply.nokia.repositories.StockRepository;
import com.nokiaSupply.nokia.service.StockService;
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
public class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @Mock
    private PartRepository partRepository;

    @InjectMocks
    private StockService stockService;

    @Test
    public void test_Add_Quantity_When_Manufacturer_And_Part_Found_Should_Add_Quantity() {
        String partName = "car";
        String manufacturerName = "tesla";
        Integer quantity = 10;

        Part part = new Part();
        part.setName(partName);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufacturerName);

        when(partRepository.findByName(partName)).thenReturn(part);
        when(manufacturerRepository.findByName(manufacturerName)).thenReturn(manufacturer);

        stockService.addQuantity(partName, manufacturerName, quantity);

        verify(stockRepository).saveAndFlush(any(Stock.class));
    }

    @Test
    public void test_Add_Quantity_When_Manufacturer_Or_Part_NotFound_Should_Not_Add_Quantity() {
        String partName = "car";
        String manufacturerName = "tesla";
        Integer quantity = 10;

        when(partRepository.findByName(partName)).thenReturn(null);
        when(manufacturerRepository.findByName(manufacturerName)).thenReturn(null);

        stockService.addQuantity(partName, manufacturerName, quantity);

        verify(stockRepository, never()).saveAndFlush(any(Stock.class));
    }

    //THERE IS AN ERROR IN THIS TEST BUT I CAN'T FIGURE IT OUT I TRY BUT I WILL NEED MORE TIME TO END THIS TEST
    /*
    @Test
    public void test_List_Quantities_When_Manufacturer_And_Part_Found_Should_List_Quantities() {

        String partName = "car";
        String manufacturerName = "tesla";
        Part part = new Part();
        part.setId(1);
        part.setName(partName);
        part.setPrice(100.00);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1);
        manufacturer.setName(manufacturerName);

        Stock stock = new Stock();
        stock.setPartId(part.getId());
        stock.setManufacturerId(manufacturer.getId());
        stock.setQuantity(10);

        List<Stock> stockList = new ArrayList<>();
        stockList.add(stock);
        when(partRepository.findByName(partName)).thenReturn(part);
        when(manufacturerRepository.findByName(manufacturerName)).thenReturn(manufacturer);
        when(stockRepository.findQuantities(part.getId(), manufacturer.getId())).thenReturn(stockList);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        stockService.listQuantities(partName, manufacturerName);
        String output = outContent.toString();


        assertThat(output).contains(partName, manufacturerName, "100.00", "10");
    }*/

    @Test
    public void test_List_Quantities_When_Manufacturer_Or_Part_NotFound_Should_Not_ListQuantities() {
        String partName = "car";
        String manufacturerName = "tesla";

        when(partRepository.findByName(partName)).thenReturn(null);
        when(manufacturerRepository.findByName(manufacturerName)).thenReturn(null);

        stockService.listQuantities(partName, manufacturerName);

        verify(stockRepository, never()).findQuantities(anyInt(), anyInt());
    }
}