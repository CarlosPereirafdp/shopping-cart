package com.nokiaSupply.nokia;

import com.nokiaSupply.nokia.entities.User;
import com.nokiaSupply.nokia.repositories.UserRepository;
import com.nokiaSupply.nokia.views.DataView;
import com.nokiaSupply.nokia.views.ManufacturersView;
import com.nokiaSupply.nokia.views.ShoppingCartView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class MainLayout {

    private final DataView dataView;
    private final ManufacturersView manufacturersView;
    private final UserRepository userRepository;
    private final ShoppingCartView shoppingCartView;


    public void createLayout() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        System.out.println("\n--- Login ---");
        System.out.println("What is your name?");
        user.setName(scanner.nextLine());
        System.out.println("What is your budget?");
        user.setBudget(scanner.nextDouble());

        setUser(user);

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1: Data");
            System.out.println("2: Manufacturers");
            System.out.println("3: Shopping Cart");
            System.out.println("4: Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewData();
                    break;
                case 2:
                    viewManufacturers();
                    break;
                case 3:
                    viewShoppingCart();
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }


    }

    private void setUser(User user) {
        userRepository.save(user);
    }

    private void viewData() {
        dataView.createLayout();
    }

    private void viewManufacturers() {
        manufacturersView.createLayout();
    }

    private void viewShoppingCart() {
        shoppingCartView.createLayout();
    }


}
