package com.nokiaSupply.nokia.views;

import com.nokiaSupply.nokia.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ManufacturersView {

    private final StockService stockService;
    private Scanner scanner = new Scanner(System.in);

    public void createLayout() {

        while (true) {
            System.out.println("\n--- Manufacturers Menu ---");
            System.out.println("1: Add quantity");
            System.out.println("2: List quantity");
            System.out.println("3: Back");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addQuantityToStock();
                    break;
                case 2:
                    listStock();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listStock() {
        scanner.nextLine();
        System.out.println("What is the name of the part?");
        String partName = scanner.nextLine();
        System.out.println("What is the name of the manufacturer?");
        String manufacturerName = scanner.nextLine();

        stockService.listQuantities(partName, manufacturerName);
    }

    private void addQuantityToStock() {
        scanner.nextLine();
        System.out.println("What is the name of the part?");
        String partName = scanner.nextLine();
        System.out.println("What is the name of the manufacturer?");
        String manufacturerName = scanner.nextLine();
        System.out.println("What is the quantity?");
        Integer quantity = scanner.nextInt();

        stockService.addQuantity(partName, manufacturerName, quantity);
    }
}
