package com.agendamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Remova a exclusão de SecurityAutoConfiguration
public class MediUjcApplication {
    public static void main(String[] args) {
        SpringApplication.run(MediUjcApplication.class, args);
    }
}