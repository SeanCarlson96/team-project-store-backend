package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.DTOs.CartDTO;
import net.yorksolutions.fafoshop.models.AppUser;
import net.yorksolutions.fafoshop.models.Cart;
import net.yorksolutions.fafoshop.models.ProductInCart;
import net.yorksolutions.fafoshop.repositories.AppUserRepo;
import net.yorksolutions.fafoshop.repositories.CartRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepo cartRepo;
    private final AppUserRepo appUserRepo;

    public CartService(CartRepo cartRepo, AppUserRepo appUserRepo) {
        this.cartRepo = cartRepo;
        this.appUserRepo = appUserRepo;
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
        cart.setProducts(cart.getProducts());
        //cart.setUser(cart.getUser());

        cartRepo.save(cart);

//        if (cartRequest.getUser() != null) {
//            Optional<AppUser> appUserOptional = appUserRepo.findById(cart.getUser().getId());
//            if (appUserOptional.isEmpty())
//                throw new Exception();
//
//            AppUser appUser = appUserOptional.get();
//            appUser.getCarts().add(cart);
//            appUserRepo.save(appUser);
//        }
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
