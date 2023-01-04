package net.yorksolutions.fafoshop.DTOs;

import net.yorksolutions.fafoshop.models.Cart;

import java.util.Optional;


public class AppUserDTO {
    public Optional<Long> id;
    public String email;
    public String password;
    public String userType;
    public Iterable<CartDTO> carts;
    public Iterable<CouponDTO> coupons;
}
