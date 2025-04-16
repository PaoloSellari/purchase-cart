package it.subito.purchase.repository;

import it.subito.purchase.entity.Product;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface ProductRepository extends JpaRepositoryImplementation<Product, Long> {
}