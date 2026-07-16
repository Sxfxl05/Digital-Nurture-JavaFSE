package com.banking;

import com.banking.model.Account;
import com.banking.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner seedDatabase(AccountRepository repository) {
        return args -> {
            repository.save(new Account(101L, "Safal Chaturvedi", 50000.00));
            repository.save(new Account(102L, "Aditya Sharma", 1500.00));
            System.out.println("\n[Database Seed] Successfully seeded mock profiles via Spring Data JPA!\n");
        };
    }
}
