package net.yorksolutions.fafoshop.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.yorksolutions.fafoshop.DTOs.CartDTO;
import net.yorksolutions.fafoshop.models.AppUser;
import net.yorksolutions.fafoshop.DTOs.ProductInCartDTO;
import net.yorksolutions.fafoshop.models.Cart;
import net.yorksolutions.fafoshop.models.Product;
import net.yorksolutions.fafoshop.models.ProductInCart;
import net.yorksolutions.fafoshop.repositories.AppUserRepo;
import net.yorksolutions.fafoshop.repositories.CartRepo;
import net.yorksolutions.fafoshop.repositories.ProductInCartRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CartService {

    private final CartRepo cartRepo;
    private final ProductInCartRepo productInCartRepo;
    private final AppUserRepo appUserRepo;

    private final ProductInCartService productInCartService;


    public CartService(CartRepo cartRepo, AppUserRepo appUserRepo,ProductInCartRepo productInCartRepo, ProductInCartService productInCartService) {
        this.cartRepo = cartRepo;
        this.productInCartRepo = productInCartRepo;
        this.appUserRepo = appUserRepo;
        this.productInCartService = productInCartService;
    }

    public Iterable<Cart> getAll() {
        return cartRepo.findAll();
    }

    public Cart getCartById(Long id) {
        return cartRepo.findById(id).orElse(null);
    }

    public void createCart(CartDTO cartRequest) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Optional<AppUser> appUserOptional = appUserRepo.findById(cartRequest.userId);
        if (appUserOptional.isEmpty())
            throw new Exception();

        Cart cart = new Cart();
        cart.setPurchaseDate(cartRequest.purchaseDate);
        System.out.println(mapper.writeValueAsString(cartRequest));

        Set<ProductInCart> productsInCart = productInCartService.createProductInCart(cartRequest);

        cart.setProducts(productsInCart);
        AppUser appUser = appUserOptional.get();

        Cart savedCart = cartRepo.save(cart);

        appUser.getCarts().add(savedCart);
        appUserRepo.save(appUser);
        System.out.println(mapper.writeValueAsString(savedCart));
        System.out.println(mapper.writeValueAsString(appUser));
    }

    public void updateCart(Long cartId, ProductInCartDTO product) throws Exception {
        Optional<Cart> cart = cartRepo.findById(cartId);

        if (cart.isEmpty()) {
            throw new Exception();
        }

        Cart cartUpdated = cart.get();


        Set<ProductInCart> productInCart = productInCartService.updateProductInCart(product);


        cartUpdated.setProducts(productInCart);
        cartRepo.save(cartUpdated);

    }


    public void deleteCartById(Long id) throws Exception {
        Optional<Cart> cartOptional = cartRepo.findById(id);

        if (cartOptional.isEmpty())
            throw new Exception();

        cartRepo.deleteById(id);
    }
}
