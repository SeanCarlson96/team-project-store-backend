package net.yorksolutions.fafoshop.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String email;
    public String password;
    public String userType;

    @OneToMany
    public Set<Cart> carts;

    @OneToMany
    public Set<Coupon> coupons;

    public AppUser(Long id, String email, String password, String userType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
    public AppUser(){}
}
