package net.yorksolutions.fafoshop.DTOs;

import net.yorksolutions.fafoshop.models.Product;

import java.util.Optional;


public class CategoryDTO {
    public Optional<Long> id;
    public String categoryName;
    public Iterable<ProductDTO> products;
}
