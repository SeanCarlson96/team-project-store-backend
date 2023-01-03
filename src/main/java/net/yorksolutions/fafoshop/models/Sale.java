package net.yorksolutions.fafoshop.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String saleName;
    private Date startDate;
    private Date stopDate;
    @Cascade(CascadeType.ALL)
    @OneToMany
    private Set<Product> products;
    private Double discountPercentage;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
