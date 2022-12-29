package net.yorksolutions.fafoshop.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String categoryName;
    @ManyToMany
    public Set<Product> products;
    public Category(Long id, String categoryName, Set<Product> products) {
        this.id = id;
        this.categoryName = categoryName;
        this.products = products;
    }
    public Category() {
    }
}
