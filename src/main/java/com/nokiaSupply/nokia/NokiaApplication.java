package com.nokiaSupply.nokia;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = "com.nokiaSupply")
public class NokiaApplication {
    private final MainLayout mainLayout;

    public static void main(String[] args) {
        SpringApplication.run(NokiaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> mainLayout.createLayout();
    }
}
