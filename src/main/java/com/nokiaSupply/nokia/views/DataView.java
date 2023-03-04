package com.nokiaSupply.nokia.views;

import com.nokiaSupply.nokia.entities.Manufacturer;
import com.nokiaSupply.nokia.entities.transfer.ManufaturerRequest;
import com.nokiaSupply.nokia.entities.transfer.PartRequest;
import com.nokiaSupply.nokia.repositories.ManufacturerRepository;
import com.nokiaSupply.nokia.service.ManufacturerService;
import com.nokiaSupply.nokia.service.PartService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;



@Component
@RequiredArgsConstructor
public class DataView {

    private final PartService partService;

    private final ManufacturerService manufacturerService;
    private Scanner scanner = new Scanner(System.in);

    public void createLayout() {


        while (true) {
            System.out.println("\n--- Data Menu ---");
            System.out.println("1: Add part");
            System.out.println("2: Add manufacturer");
            System.out.println("3: Remove manufacturer");
            System.out.println("4: Back");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addPart();
                    break;
                case 2:
                    addManufacturer();
                    break;
                case 3:
                    removeManufacturer();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void addPart() {
       System.out.println("What is the name of the part?");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Put the manufaturer of this part?");
        String manufacturer = scanner.nextLine();
        System.out.println("Put the price of this part?");
        double price = scanner.nextDouble();

        PartRequest partRequest = new PartRequest(name, manufacturer,price);
        partService.addPart(partRequest);
        scanner.nextLine();
    }

    private void addManufacturer() {
        scanner.nextLine();
        System.out.println("What is the name of the manufacturer?");
        String name = scanner.nextLine();

        ManufaturerRequest manufaturerRequest = new ManufaturerRequest(name);
        manufacturerService.addManufaturer(manufaturerRequest);
    }

    private void removeManufacturer() {
        scanner.nextLine();
        System.out.println("What is the name of the manufacturer you want to remove?");
        String name = scanner.nextLine();
        manufacturerService.removeManufacturer(name);
    }


}

