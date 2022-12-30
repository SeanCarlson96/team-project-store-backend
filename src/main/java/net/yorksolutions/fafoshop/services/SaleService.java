package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.models.Sale;
import net.yorksolutions.fafoshop.repositories.SaleRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepo saleRepo;

    public SaleService(SaleRepo saleRepo) {
        this.saleRepo = saleRepo;
    }

    public Iterable<Sale> getAllSales() {
        return saleRepo.findAll();
    }

    public Sale getSaleById(Long id) throws Exception {
        return saleRepo.findById(id).orElse(null);
    }

    public void createSale(Sale saleRequest) throws Exception {
        Optional<Sale> saleOptional = saleRepo.findSaleBySaleName(saleRequest.getSaleName());

        if (saleOptional.isPresent())
            throw new Exception();

        Sale sale = new Sale();

        sale.setSaleName(saleRequest.getSaleName());
        sale.setStartDate(saleRequest.getStartDate());
        sale.setStopDate(saleRequest.getStopDate());
        sale.setProducts(saleRequest.getProducts());
        sale.setDiscountPercentage(saleRequest.getDiscountPercentage());

        // TODO - post sale logic

        saleRepo.save(sale);
    }

    public void deleteSaleById(Long id) throws Exception {
        Optional<Sale> saleOptional = saleRepo.findById(id);

        if (saleOptional.isEmpty())
            throw new Exception();

        saleRepo.deleteById(id);
    }

    public void modifySale(Long id, Sale saleRequest) throws Exception {
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
