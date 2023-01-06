package net.yorksolutions.fafoshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String productName;
    private Double price;
    @Cascade(CascadeType.ALL)
    @JsonIgnoreProperties("products")
    @ManyToOne
    private Sale sale;
    @JsonIgnoreProperties("products")
    @ManyToMany
    private Set<Category> categories;
    private String description;
    private Boolean discontinued;
    private String image;
    private Date availableDate;
    private Integer quantity;
    private Double minAdPrice;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getMinAdPrice() {
        return minAdPrice;
    }

    public void setMinAdPrice(Double minAdPrice) {
        this.minAdPrice = minAdPrice;
    }
}
