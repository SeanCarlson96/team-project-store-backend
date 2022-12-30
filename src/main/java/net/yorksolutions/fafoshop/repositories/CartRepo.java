package net.yorksolutions.fafoshop.repositories;

import net.yorksolutions.fafoshop.models.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends CrudRepository<Cart, Long> {

}
