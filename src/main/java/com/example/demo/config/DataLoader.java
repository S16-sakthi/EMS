package com.example.demo.config;

import com.example.demo.model.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            repository.save(new Employee("Ada", "Lovelace", "ada@example.com", "Engineering", 120000.0));
            repository.save(new Employee("Alan", "Turing", "alan@example.com", "Research", 125000.0));
            repository.save(new Employee("Grace", "Hopper", "grace@example.com", "DevOps", 115000.0));
        };
    }
}