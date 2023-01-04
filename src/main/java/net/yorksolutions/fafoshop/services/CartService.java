package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.models.Cart;
import net.yorksolutions.fafoshop.models.Product;
import net.yorksolutions.fafoshop.models.ProductInCart;
import net.yorksolutions.fafoshop.repositories.CartRepo;
import net.yorksolutions.fafoshop.repositories.ProductInCartRepo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    private final CartRepo cartRepo;
    private final ProductInCartRepo productInCartRepo;
    private CartRepo cartRepo1;

    public CartService(CartRepo cartRepo, ProductInCartRepo productInCartRepo) {
        this.cartRepo = cartRepo;
        this.productInCartRepo = productInCartRepo;
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
        cart.setPurchaseDate(cart.getPurchaseDate());

        cartRepo.save(cart);


    }

    public void updateCart(Long cartId, ProductInCart product) throws Exception {
        Optional<Cart> cart = cartRepo.findById(cartId);

        if (cart.isEmpty()) {
            throw new Exception();
        }

        Cart cartUpdated = cart.get();

        cartUpdated.getProducts().add(product);
        cartRepo.save(cartUpdated);

    }


    public void deleteCartById(Long id) throws Exception {
        Optional<Cart> cartOptional = cartRepo.findById(id);

        if (cartOptional.isEmpty())
            throw new Exception();

        cartRepo.deleteById(id);
    }
}
