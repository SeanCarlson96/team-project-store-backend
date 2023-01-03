package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.models.Product;
import net.yorksolutions.fafoshop.models.Sale;
import net.yorksolutions.fafoshop.repositories.ProductRepo;
import net.yorksolutions.fafoshop.repositories.SaleRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void createSale(Sale saleRequest) throws Exception {
        Optional<Sale> saleOptional = saleRepo.findSaleBySaleName(saleRequest.getSaleName());

        if (saleOptional.isPresent())
            throw new Exception("Sale name already exists");

        Sale sale = new Sale();

        sale.setSaleName(saleRequest.getSaleName());
        sale.setStartDate(saleRequest.getStartDate());
        sale.setStopDate(saleRequest.getStopDate());
        sale.setProducts(saleRequest.getProducts());
        sale.setDiscountPercentage(saleRequest.getDiscountPercentage());

        // TODO - post sale logic

        saleRepo.save(sale);

        for (Product saleProduct: sale.getProducts()) {
            Optional<Product> productOptional = productRepo.findById(saleProduct.getId());

            if (productOptional.isEmpty())
                throw new Exception("Product id does not exist");

            Product product = productOptional.get();
            product.setSale(sale);
            productRepo.save(product);
        }
    }

    public void deleteSaleById(Long id) throws Exception {
        Optional<Sale> saleOptional = saleRepo.findById(id);

        if (saleOptional.isEmpty())
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
