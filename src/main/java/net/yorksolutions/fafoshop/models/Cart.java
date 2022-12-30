package net.yorksolutions.fafoshop.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public Date purchaseDate;

    @OneToMany
    public Set<ProductInCart> products;

    public Cart(Long id, Date purchaseDate, Set<ProductInCart> products) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.products = products;
    }
    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Set<ProductInCart> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductInCart> products) {
        this.products = products;
    }
}
