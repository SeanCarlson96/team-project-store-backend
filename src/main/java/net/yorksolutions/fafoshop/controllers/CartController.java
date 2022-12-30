package net.yorksolutions.fafoshop.controllers;

import net.yorksolutions.fafoshop.models.Cart;
import net.yorksolutions.fafoshop.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/carts")
@CrossOrigin
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Iterable<Cart> getAll() {
        return cartService.getAll();
    }

    @GetMapping(params = {"id"})
    public Cart getCartById(@RequestParam Long id) {
        try {
            return cartService.getCartById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void createCart(@RequestBody Cart cart) {
        try {
            cartService.createCart(cart);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCartById(@PathVariable Long id){
        try{
            cartService.deleteCartById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
