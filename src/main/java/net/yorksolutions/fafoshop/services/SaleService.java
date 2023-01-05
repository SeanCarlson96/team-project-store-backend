package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.DTOs.ProductDTO;
import net.yorksolutions.fafoshop.DTOs.SaleDTO;
import net.yorksolutions.fafoshop.models.Product;
import net.yorksolutions.fafoshop.models.Sale;
import net.yorksolutions.fafoshop.repositories.ProductRepo;
import net.yorksolutions.fafoshop.repositories.SaleRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SaleService {

    private final SaleRepo saleRepo;
    private final ProductRepo productRepo;

    public SaleService(SaleRepo saleRepo, ProductRepo productRepo) {
        this.saleRepo = saleRepo;
        this.productRepo = productRepo;
    }

    public Iterable<Sale> getAllSales() {
        return saleRepo.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepo.findById(id).orElse(null);
    }

    public void createSale(SaleDTO saleRequest) throws Exception {
        if (saleRepo.findSaleBySaleName(saleRequest.saleName).isPresent())
            throw new Exception("Sale name already exists");

        Sale sale = new Sale();
        Set<Product> productSet = new HashSet<>();
        sale.setSaleName(saleRequest.saleName);
        sale.setStartDate(saleRequest.startDate);
        sale.setStopDate(saleRequest.stopDate);
        sale.setDiscountPercentage(saleRequest.discountPercentage);

        for (ProductDTO product: saleRequest.products) {
            Optional<Product> productOptional = productRepo.findById(product.id.get());
            if (productOptional.isEmpty())
                throw new Exception();

            productSet.add(productOptional.get());
        }

        sale.setProducts(productSet);
        Sale savedSale = saleRepo.save(sale);

        for (Product saleProduct: savedSale.getProducts()) {
            Optional<Product> productOptional = productRepo.findById(saleProduct.getId());
            if (productOptional.isEmpty())
                throw new Exception("Product id does not exist");

            Product product = productOptional.get();
            product.setSale(savedSale);
            productRepo.save(product);
        }
    }
// sale dto in dto , update prod dto
    public void deleteSaleById(Long id) throws Exception {
        if (saleRepo.findById(id).isEmpty())
            throw new Exception();

        saleRepo.deleteById(id);
    }

    public void updateSale(Long id, Sale saleRequest) throws Exception {
        Optional<Sale> saleOptional = saleRepo.findById(id);

        if (saleOptional.isEmpty())
            throw new Exception();

        Sale sale = saleOptional.get();
        sale.setSaleName(saleRequest.getSaleName());
        sale.setStartDate(saleRequest.getStartDate());
        sale.setStopDate(saleRequest.getStopDate());
        sale.setProducts(saleRequest.getProducts());
        sale.setDiscountPercentage(saleRequest.getDiscountPercentage());

        // TODO - modify sale logic

        saleRepo.save(sale);
    }
}
