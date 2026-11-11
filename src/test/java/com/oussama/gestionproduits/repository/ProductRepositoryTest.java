package com.oussama.gestionproduits.repository;

import com.oussama.gestionproduits.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSaveAndFindProduct() {
        Product product = new Product(null, "Test", 99.0, 5, "Produit de test");
        Product saved = productRepository.save(product);

        assertThat(saved.getId()).isNotNull();
        assertThat(productRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void shouldSearchProductByName() {
        productRepository.save(new Product(null, "Clavier", 200.0, 3, "Test recherche"));
        assertThat(productRepository.findByNameContainingIgnoreCase("clav")).hasSize(1);
    }
}
