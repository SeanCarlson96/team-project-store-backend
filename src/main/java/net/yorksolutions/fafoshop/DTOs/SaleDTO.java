package net.yorksolutions.fafoshop.DTOs;

import java.util.Date;
import java.util.Optional;

public class SaleDTO {
    public Optional<Long> id;
    public String saleName;
    public Date startDate;
    public Date stopDate;
    public Iterable<ProductDTO> products;
    public Double discountPercentage;
}
