package net.yorksolutions.fafoshop.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String userType;

    @OneToMany
    private Set<Cart> carts;

    @OneToMany
    @Cascade(CascadeType.ALL)
    @JsonIgnoreProperties("appUsers")
    private Set<Coupon> coupons;

    public AppUser(Long id, String email, String password, String userType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
    public AppUser(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public Set<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<Coupon> coupons) {
        this.coupons = coupons;
    }
}
