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
}
