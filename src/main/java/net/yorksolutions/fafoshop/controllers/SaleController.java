package net.yorksolutions.fafoshop.controllers;

import net.yorksolutions.fafoshop.DTOs.SaleDTO;
import net.yorksolutions.fafoshop.models.Sale;
import net.yorksolutions.fafoshop.services.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/sales")
public class SaleController {

    private  final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Sale> getAllSales() {
        return service.getAllSales();
    }

    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Long id) {
        try {
            return service.getSaleById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void createSale(@RequestBody SaleDTO saleRequest) {
        try {
            service.createSale(saleRequest);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteSaleById(@PathVariable Long id) {
        try {
            service.deleteSaleById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void updateSale(@PathVariable Long id, @RequestBody Sale sale) {
        try {
            service.updateSale(id, sale);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
