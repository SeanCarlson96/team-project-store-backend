package net.yorksolutions.fafoshop.services;

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

        Cart cart = new Cart();
        cart.setPurchaseDate(cart.getPurchaseDate());
        cart.setProducts(cart.getProducts());

//        ProductInCartService service = new ProductInCartService(productInCartRepo);
////
//        Set<ProductInCart> productInCart = productInCartService.createProductInCart(cartRequest);
////
//        cart.setProducts(productInCart);

        cartRepo.save(cart);



        if (cartRequest.userId != null) {
            Optional<AppUser> appUserOptional = appUserRepo.findById(cart.getUser().getId());
            if (appUserOptional.isEmpty())
                throw new Exception();

            AppUser appUser = appUserOptional.get();
            appUser.getCarts().add(cart);
            appUserRepo.save(appUser);
        }
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
