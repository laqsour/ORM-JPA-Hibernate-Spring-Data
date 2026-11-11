package com.oussama.gestionproduits;

import com.oussama.gestionproduits.entity.Product;
import com.oussama.gestionproduits.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionProduitsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestionProduitsApplication.class, args);
    }

    @Bean
    CommandLineRunner loadSampleProducts(ProductRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Product(null, "Ordinateur portable", 7500.0, 8, "PC portable pour le travail"));
                repository.save(new Product(null, "Souris sans fil", 150.0, 25, "Souris USB sans fil"));
                repository.save(new Product(null, "Clavier mécanique", 450.0, 12, "Clavier mécanique AZERTY"));
            }
        };
    }
}
