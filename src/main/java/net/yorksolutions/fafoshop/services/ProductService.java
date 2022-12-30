package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.models.Product;
import net.yorksolutions.fafoshop.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Iterable<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public void createProduct(Product productRequest) throws Exception {
        Optional<Product> productOptional = productRepo.findProductByProductName(productRequest.productName);

        if (productOptional.isPresent())
            throw new Exception("Product name already exists");

        Product product = new Product();
        product.productName = productRequest.productName;
        product.price = productRequest.price;
        product.sale = productRequest.sale;
        product.categories = productRequest.categories;
        product.description = productRequest.description;
        product.discontinued = productRequest.discontinued;
        product.image = productRequest.image;
        product.availableDate = productRequest.availableDate;
        product.quantity = productRequest.quantity;
        product.minAdPrice = productRequest.minAdPrice;

        // TODO - post product logic

        productRepo.save(product);
    }

    public void deleteProductById(Long id) throws Exception {
        Optional<Product> productOptional = productRepo.findById(id);

        if (productOptional.isEmpty())
            throw new Exception();

        productRepo.deleteById(id);
    }

    public void modifyProduct(Long id, Product product) throws Exception {
        Optional<Product> productOptional = productRepo.findById(id);

        if (productOptional.isEmpty())
            throw new Exception();

        // TODO - modify product logic
    }
}
