package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.DTOs.CartDTO;
import net.yorksolutions.fafoshop.DTOs.ProductInCartDTO;
import net.yorksolutions.fafoshop.models.Cart;
import net.yorksolutions.fafoshop.models.Product;
import net.yorksolutions.fafoshop.models.ProductInCart;
import net.yorksolutions.fafoshop.repositories.ProductInCartRepo;
import net.yorksolutions.fafoshop.repositories.ProductRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProductInCartService {
    private final ProductInCartRepo productInCartRepo;
    private final ProductRepo productRepo;

    public ProductInCartService(ProductInCartRepo productInCartRepo, ProductRepo productRepo){
        this.productInCartRepo = productInCartRepo;
        this.productRepo = productRepo;
    }

    public Set<ProductInCart> createProductInCart(CartDTO cartRequest){
        Set<ProductInCart> productsInCart = new HashSet<>();

        //Take productInCart iterable from CartDto
        // convert productInCartDto to productInCart to save repo
        for (ProductInCartDTO productDTO: cartRequest.products) {
            ProductInCart productInCart = new ProductInCart();
            Optional<Product> product = productRepo.findById(productDTO.id.get());

            productInCart.setProduct(product.get());
            productInCart.setQuantity(productDTO.quantity);


            ProductInCart savedProductInCart = productInCartRepo.save(productInCart);
            productsInCart.add(savedProductInCart);
        }

        return productsInCart;

    }

    public void updateProductInCart(){

    }
}
