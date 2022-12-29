package net.yorksolutions.fafoshop.models;

import javax.persistence.*;

@Entity
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    @OneToOne
    public Product product;
    public Long quantity;

    public ProductInCart(Long id, Product product, Long quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }
    public ProductInCart() {
    }
}
