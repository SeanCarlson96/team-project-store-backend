package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.models.Cart;
import net.yorksolutions.fafoshop.models.ProductInCart;
import net.yorksolutions.fafoshop.repositories.ProductInCartRepo;

import java.util.HashSet;
import java.util.Set;

public class ProductInCartService {
    private final ProductInCartRepo productInCartRepo;

    public ProductInCartService(ProductInCartRepo productInCartRepo){
        this.productInCartRepo = productInCartRepo;
    }

    public Set<ProductInCart> createProductInCart(Cart cartRequest){
        Set<ProductInCart> productsInCart = new HashSet<>();

        for (ProductInCart product : cartRequest.getProducts()) {
            ProductInCart savedProductInCart = productInCartRepo.save(product);
            productsInCart.add(savedProductInCart);
        }

//        cart.setProducts(productsInCart);
        return productsInCart;

    }

    public void updateProductInCart(){

    }
}
