package com.nokiaSupply.nokia.config;

import com.nokiaSupply.nokia.entities.Manufacturer;
import com.nokiaSupply.nokia.entities.Part;
import com.nokiaSupply.nokia.entities.Stock;
import com.nokiaSupply.nokia.repositories.ManufacturerRepository;
import com.nokiaSupply.nokia.repositories.PartRepository;
import com.nokiaSupply.nokia.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@Profile("test")
public class ConfigDB implements CommandLineRunner {

    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private final ManufacturerRepository manufacturerRepository;
    private final PartRepository partRepository;

    private final StockRepository stockRepository;

    @Override
    public void run(String... args) throws Exception {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to database successfully.");
            Statement stmt = conn.createStatement();

            String createManufacturers = """
                    CREATE TABLE IF NOT EXISTS manufacturer (
                    manufacturer_id INT PRIMARY KEY, 
                    manufacturer_name VARCHAR(255)  
                    )
                    """;
            stmt.executeUpdate(createManufacturers);

            String createParts = """
                    CREATE TABLE IF NOT EXISTS parts (
                    part_id INT PRIMARY KEY, 
                    part_name VARCHAR(255),
                    sold NUMBER(1), 
                    price INT,                               
                    manufacturer_id INT, 
                    FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(manufacturer_id)
                    )
                    """;
            stmt.executeUpdate(createParts);

            String createPerson = """
                    CREATE TABLE IF NOT EXISTS person (
                    person_id INT PRIMARY KEY, 
                    person_name VARCHAR(255), 
                    budget DOUBLE
                    )
                    """;
            stmt.executeUpdate(createPerson);

            // create stock table
            String createStock = """
                    CREATE TABLE IF NOT EXISTS stock (
                    id INT PRIMARY KEY, 
                    part_id INT, 
                    quantity INT,
                    manufacturer_id INT, 
                    FOREIGN KEY (part_id) REFERENCES Parts(part_id),
                    FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(manufacturer_id)
                    )
                    """;
            stmt.executeUpdate(createStock);

            // create Manufacturer and Parts for testing
            Manufacturer manufacturer = new Manufacturer(null, "Nokia");
            Part part = new Part(null, "Telemovel", manufacturer, 10.0, false);
            manufacturer = manufacturerRepository.save(manufacturer);
            partRepository.save(part);

            List<Part> parts = new ArrayList<>();
            parts.add(part);
            manufacturer.setPartsList(parts);

            Stock stock = new Stock(null, 1, 1, 10);
            stockRepository.save(stock);

            String createCart = """
                    CREATE TABLE IF NOT EXISTS shop_cart (
                    id INT PRIMARY KEY, 
                    part_id INT, 
                    manufacturer_id INT,
                    parts_quantity INT,
                    total_price DOUBLE, 
                    FOREIGN KEY (part_id) REFERENCES Parts(part_id),
                    FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(manufacturer_id)
                    )
                    """;
            stmt.executeUpdate(createCart);


            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            e.printStackTrace();
        }
    }
}
