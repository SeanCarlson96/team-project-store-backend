package net.yorksolutions.fafoshop.DTOs;

import net.yorksolutions.fafoshop.models.Category;
import net.yorksolutions.fafoshop.models.Sale;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public class ProductDTO {
    public Optional<Long> id;
    public String productName;
    public Double price;

    public SaleDTO saleId;

    public Iterable<CategoryDTO> categories;

    public String description;
    public Boolean discontinued;
    public String image;
    public Date availableDate;
    public Integer quantity;
    public Double minAdPrice;
}
