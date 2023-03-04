package com.nokiaSupply.nokia.service;

import com.nokiaSupply.nokia.entities.Part;
import com.nokiaSupply.nokia.entities.ShopCart;
import com.nokiaSupply.nokia.entities.Stock;
import com.nokiaSupply.nokia.entities.User;
import com.nokiaSupply.nokia.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Service
public class ShoppingCartService {

    private final UserRepository userRepository;
    private final PartRepository partRepository;
    private final StockRepository stockRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private Scanner scanner = new Scanner(System.in);

    @Transactional(propagation = Propagation.REQUIRED)
    public void addMoneyToUser(String name, double money) {
        User user = userRepository.findByName(name);

        if (user != null) {
            user.setBudget(user.getBudget() + money);
            userRepository.saveAndFlush(user);
            System.out.println(String.format("Your budget now is:%,.2f", user.getBudget()));
        } else {
            System.out.println("User not found");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void buyParts(String userName, String partName, Integer quantity) {
        User user = userRepository.findByName(userName);
        Part part = partRepository.findByName(partName);
        if (user != null && part != null) {
            createCart(user, quantity, part);
        } else {
            System.out.println("Your name doesnt exist or this part doenst exist in DB!");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void listCart() {
        List<ShopCart> shopCartList = shoppingCartRepository.findAll();

        for (ShopCart cart : shopCartList) {
            System.out.println(String.format("\nPart:%s, Manufacturer:%s, Quantity=%d ",
                    partRepository.findById(cart.getPartId()).get().getName(),
                    manufacturerRepository.findById(cart.getManufacturerId()).get().getName(),
                    cart.getPartsQuantity()));
            scanner.nextLine();
        }

    }

    private void createCart(User user, Integer quantity, Part part) {

        Stock stock = stockRepository.findStockByPartId(part.getId());
        if (stock != null) {
            if (stock.getQuantity() >= quantity && (quantity * part.getPrice()) <= user.getBudget()) {

                ShopCart shopCart = new ShopCart();
                shopCart.setPartId(part.getId());
                shopCart.setManufacturerId(stock.getManufacturerId());
                shopCart.setPartsQuantity(quantity);
                shopCart.setTotalPrice(quantity * part.getPrice());
                shoppingCartRepository.saveAndFlush(shopCart);

                //remove from stock the amount of parts buyed by user, if stock is empty them remove part from stock and set sold to true
                stock.setQuantity(stock.getQuantity() - quantity);
                user.setBudget(user.getBudget() - (quantity * part.getPrice()));
                if (stock.getQuantity() <= 0) {
                    stockRepository.delete(stock);
                    part.setSold(true);
                }
                System.out.println("------ Cart -----");
                System.out.println(String.format("Part:%s , Price:%,.2f", part.getName(), part.getPrice()));
                System.out.println(String.format("Quantity:%d", shopCart.getPartsQuantity()));
                System.out.println(String.format("Total to pay:%,.2f", shopCart.getTotalPrice()));
                System.out.println(String.format("Your cart have now %d - %s and the amount to pay is %,.2f", shopCart.getPartsQuantity(), part.getName(), shopCart.getTotalPrice()));
                scanner.nextLine();
            } else {
                System.out.println("You dont have enough money or doesn't exist this amount of parts.");
            }
        } else {
            System.out.println("Doenst have stock with this part, please add stock first.");
        }
    }


}
