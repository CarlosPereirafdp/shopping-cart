package com.nokiaSupply.nokia;


import com.nokiaSupply.nokia.entities.Manufacturer;
import com.nokiaSupply.nokia.entities.Part;
import com.nokiaSupply.nokia.entities.Stock;
import com.nokiaSupply.nokia.entities.User;
import com.nokiaSupply.nokia.repositories.*;
import com.nokiaSupply.nokia.service.ShoppingCartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ShoppingCartServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PartRepository partRepository;
    @Mock
    private StockRepository stockRepository;
    @Mock
    private ManufacturerRepository manufacturerRepository;
    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    private ShoppingCartService shoppingCartService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        shoppingCartService = new ShoppingCartService(userRepository, partRepository, stockRepository, manufacturerRepository, shoppingCartRepository);
    }

    @Test
    public void test_Add_Money_To_User() {
        User user = new User(1, "John", 100.0);

        when(userRepository.findByName("John")).thenReturn(user);

        shoppingCartService.addMoneyToUser("John", 50.0);

        Assertions.assertEquals(user.getBudget(), 150.0);
        verify(userRepository, times(1)).saveAndFlush(user);
    }

    @Test
    public void test_Buy_Parts_should_not_buy_and_display_not_enough_money() {
        User user = new User(1, "Carlos", 100.0);

        when(userRepository.findByName("Carlos")).thenReturn(user);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1);
        manufacturer.setName("tesla");

        Part part = new Part(1, "car", manufacturer, 100.0, false);
        when(partRepository.findByName("car")).thenReturn(part);

        Stock stock = new Stock(1, part.getId(), manufacturer.getId(), 1);
        when(stockRepository.findStockByPartId(part.getId())).thenReturn(stock);

        shoppingCartService.buyParts("Carlos", "car", 2);

        Assertions.assertEquals(user.getBudget(), 100.0);
    }

}
