package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.models.Cart;
import net.yorksolutions.fafoshop.repositories.CartRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepo cartRepo;
    private CartRepo cartRepo1;

    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }
    public Iterable<Cart> getAll() {
        return cartRepo.findAll();
    }

    public Cart getCartById(Long id) {
        return cartRepo.findById(id).orElse(null);
    }

    public void createCart(Cart cartRequest) throws Exception {
        Optional<Cart> cartOptional = cartRepo.findById(cartRequest.getId());

        if (cartOptional.isPresent())
            throw new Exception();

        Cart cart = new Cart();
        cart.setId(cart.getId());
        cart.setPurchaseDate(cart.getPurchaseDate());
        cart.setProducts(cart.getProducts());

        cartRepo.save(cart);
    }

    public void deleteCartById(Long id) throws Exception{
        Optional<Cart> cartOptional = cartRepo.findById(id);

        if(cartOptional.isEmpty())
            throw new Exception();

        cartRepo.deleteById(id);
    }
}
