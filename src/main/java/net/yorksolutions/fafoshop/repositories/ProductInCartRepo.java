package net.yorksolutions.fafoshop.repositories;

import net.yorksolutions.fafoshop.models.ProductInCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInCartRepo extends CrudRepository<ProductInCart, Long> {
}
