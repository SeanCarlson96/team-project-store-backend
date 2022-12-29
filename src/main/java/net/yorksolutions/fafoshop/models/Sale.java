package net.yorksolutions.fafoshop.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String saleName;
    public Date startDate;
    public Date stopDate;
    @OneToMany
    public Set<Product> products;
    public Double discountPercentage;

    public Sale(Long id, String saleName, Date startDate, Date stopDate, Set<Product> products, Double discountPercentage) {
        this.id = id;
        this.saleName = saleName;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.products = products;
        this.discountPercentage = discountPercentage;
    }
    public Sale() {
    }
}
