package net.yorksolutions.fafoshop.repositories;

import net.yorksolutions.fafoshop.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {
    Optional<Product> findProductByProductName(String productName);
}
