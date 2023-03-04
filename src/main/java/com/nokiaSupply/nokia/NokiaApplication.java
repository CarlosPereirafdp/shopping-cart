package com.nokiaSupply.nokia;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = "com.nokiaSupply")
public class NokiaApplication{
    private final MainLayout mainLayout;

    public static void main(String[] args) {
        SpringApplication.run(NokiaApplication.class, args);
    }

  /*  @Override
    public void run(String... args) throws Exception {
        mainLayout.createLayout();
    }*/
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> mainLayout.createLayout();
    }
}
