package net.yorksolutions.fafoshop.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String productName;
    public Double price;
    @ManyToOne
    public Sale sale;
    @ManyToMany
    public Set<Category> categories;
    public String description;
    public Boolean discontinued;
    public String image;
    public Date availableDate;
    public Integer quantity;
    public Double minAdPrice;

    public Product(Long id, String productName, Double price, Sale sale, Set<Category> categories, String description, Boolean discontinued, String image, Date availableDate, Integer quantity, Double minAdPrice) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.sale = sale;
        this.categories = categories;
        this.description = description;
        this.discontinued = discontinued;
        this.image = image;
        this.availableDate = availableDate;
        this.quantity = quantity;
        this.minAdPrice = minAdPrice;
    }

    public Product() {
    }
}
