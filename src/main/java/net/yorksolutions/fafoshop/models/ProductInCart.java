package net.yorksolutions.fafoshop.models;

import javax.persistence.*;

@Entity
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Product product;
    private Long quantity;

    public ProductInCart(Long id, Product product, Long quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }
    public ProductInCart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
