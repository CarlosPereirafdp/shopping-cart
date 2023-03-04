package com.nokiaSupply.nokia.views;

import com.nokiaSupply.nokia.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ShoppingCartView {

    private final ShoppingCartService shoppingCartService;

    private Scanner scanner = new Scanner(System.in);

    public void createLayout() {


        while (true) {
            System.out.println("\n--- Shopping Cart Menu ---");
            System.out.println("1: Add money to your budget");
            System.out.println("2: Buy parts");
            System.out.println("3: Show cart");
            System.out.println("4: Back");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addMoney();
                    break;
                case 2:
                    buyParts();
                    break;
                case 3:
                    showCart();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showCart() {
        shoppingCartService.listCart();
    }

    private void buyParts() {
        scanner.nextLine();
        System.out.println("Waht is you name?");
        String name = scanner.nextLine();
        System.out.println("Waht is the name of part you to to buy?");
        String part = scanner.nextLine();
        System.out.println("Waht is the quantity of parts?");
        Integer quantity = scanner.nextInt();
        shoppingCartService.buyParts(name, part, quantity);
    }

    private void addMoney() {
        scanner.nextLine();
        System.out.println("Waht is you name?");
        String name = scanner.nextLine();
        System.out.println("Put the amount of money to add?");
        double money = scanner.nextDouble();
        shoppingCartService.addMoneyToUser(name, money);
    }
}
