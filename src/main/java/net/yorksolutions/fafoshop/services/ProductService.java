package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.models.Category;
import net.yorksolutions.fafoshop.models.Product;
import net.yorksolutions.fafoshop.models.Sale;
import net.yorksolutions.fafoshop.repositories.CategoryRepo;
import net.yorksolutions.fafoshop.repositories.ProductRepo;
import net.yorksolutions.fafoshop.repositories.SaleRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final SaleRepo saleRepo;

    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo, SaleRepo saleRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.saleRepo = saleRepo;
    }

    public Iterable<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public void createProduct(Product productRequest) throws Exception {
        Optional<Product> productOptional = productRepo.findProductByProductName(productRequest.getProductName());

        if (productOptional.isPresent())
            throw new Exception("Product name already exists");

        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        product.setSale(productRequest.getSale());
        product.setCategories(productRequest.getCategories());
        product.setDescription(productRequest.getDescription());
        product.setDiscontinued(productRequest.getDiscontinued());
        product.setImage(productRequest.getImage());
        product.setAvailableDate(productRequest.getAvailableDate());
        product.setQuantity(productRequest.getQuantity());
        product.setMinAdPrice(productRequest.getMinAdPrice());

        // TODO - post product logic

        productRepo.save(product);

        if (productRequest.getSale() != null) {
            Optional<Sale> saleOptional = saleRepo.findSaleBySaleName(productRequest.getSale().getSaleName());

            if (saleOptional.isEmpty())
                throw new Exception("Sale name does not exist");

            Sale sale = saleOptional.get();
            sale.getProducts().add(product);
            saleRepo.save(sale);
        }

        for (Category productCategory: productRequest.getCategories()) {
            Optional<Category> categoryOptional = categoryRepo.findCategoryByCategoryName(productCategory.getCategoryName());

            if (categoryOptional.isEmpty())
                throw new Exception("Category name does not exist");

            Category category = categoryOptional.get();
            category.getProducts().add(product);
            categoryRepo.save(category);
        }
    }

    public void deleteProductById(Long id) throws Exception {
        Optional<Product> productOptional = productRepo.findById(id);

        if (productOptional.isEmpty())
            throw new Exception();

        productRepo.deleteById(id);
    }

    public void updateProduct(Long id, Product productRequest) throws Exception {
        Optional<Product> productOptional = productRepo.findById(id);

        if (productOptional.isEmpty())
            throw new Exception();

        // TODO - modify product logic

        Product product = productOptional.get();

        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        product.setSale(productRequest.getSale());
        product.setCategories(productRequest.getCategories());
        product.setDescription(productRequest.getDescription());
        product.setDiscontinued(productRequest.getDiscontinued());
        product.setImage(productRequest.getImage());
        product.setAvailableDate(productRequest.getAvailableDate());
        product.setQuantity(productRequest.getQuantity());
        product.setMinAdPrice(productRequest.getMinAdPrice());

        productRepo.save(product);
    }
}
