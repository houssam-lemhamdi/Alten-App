package com.product.trial.master.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MongoImportConfig {
    @Value("${app.datasource.type}")
    private String dataSourceType;
    @Bean
    public CommandLineRunner importData() {
        return args -> {
            if ("nosql".equalsIgnoreCase(dataSourceType)) {
                try {
                    String command = "mongoimport --uri=mongodb://localhost:27017 --db=product-management-db --collection=products --file=src/main/resources/mongodb.json --jsonArray --drop";
                    Process process = Runtime.getRuntime().exec(command);
                    int exitCode = process.waitFor();
                    if (exitCode == 0) {
                        System.out.println("MongoDB data initialized successfully.");
                    } else {
                        System.out.println("Failed to initialize MongoDB data.");
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Skipping MongoDB data import as data source type is not 'nosql'.");
            }
        };
    }
}
