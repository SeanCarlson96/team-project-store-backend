package net.yorksolutions.fafoshop.DTOs;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public class CartDTO {
    public Optional<Long> id;
    public Date purchaseDate;
    public Iterable<ProductInCartDTO> products;
}
