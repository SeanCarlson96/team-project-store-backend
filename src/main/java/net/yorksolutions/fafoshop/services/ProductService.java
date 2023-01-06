package net.yorksolutions.fafoshop.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.yorksolutions.fafoshop.DTOs.CategoryDTO;
import net.yorksolutions.fafoshop.DTOs.ProductDTO;
import net.yorksolutions.fafoshop.models.Category;
import net.yorksolutions.fafoshop.models.Product;
import net.yorksolutions.fafoshop.models.Sale;
import net.yorksolutions.fafoshop.repositories.CategoryRepo;
import net.yorksolutions.fafoshop.repositories.ProductRepo;
import net.yorksolutions.fafoshop.repositories.SaleRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

    public void createProduct(ProductDTO productRequest) throws Exception {
        Product product = new Product();
        Set<Category> categorySet = new HashSet<>();

        // if there is a sale, add the sale to product entity
        if (productRequest.saleId != null) {
            Optional<Sale> saleOptional = saleRepo.findById(productRequest.saleId);
            if (saleOptional.isEmpty())
                throw new Exception();

            Sale sale = saleOptional.get();
            product.setSale(sale);
        }

        // find categories from product request and add to category set to save later in product_category
        for (CategoryDTO categoryDTO: productRequest.categories) {
            if (categoryDTO.id.isPresent()) {
                Optional<Category> categoryOptional = categoryRepo.findById(categoryDTO.id.get());
                if (categoryOptional.isEmpty())
                    throw new Exception();

                Category category = categoryOptional.get();
                categorySet.add(category);
            }
        }

        product.setProductName(productRequest.productName);
        product.setPrice(productRequest.price);
        product.setCategories(categorySet); // saves categories in product
        product.setDescription(productRequest.description);
        product.setDiscontinued(productRequest.discontinued);
        product.setImage(productRequest.image);
        product.setAvailableDate(productRequest.availableDate);
        product.setQuantity(productRequest.quantity);
        product.setMinAdPrice(productRequest.minAdPrice);

        Product savedProduct = productRepo.save(product);

        // if there is a sale, add the product to the sale entity
        if (savedProduct.getSale() != null) {
            Optional<Sale> saleOptional = saleRepo.findById(productRequest.saleId);
            if (saleOptional.isEmpty())
                throw new Exception();

            Sale sale = saleOptional.get();
            sale.getProducts().add(savedProduct);
            saleRepo.save(sale);
        }

        // this is local variable to store updated categories
        List<Category> categoryList = new ArrayList<>();

        for (Category categoryProduct: savedProduct.getCategories()) {
            // getting one category with matching id
            Optional<Category> categoryOptional = categoryRepo.findById(categoryProduct.getId());
            // extracting info from optional
            Category category = categoryOptional.get();
            // adds the saved product to the products set
            category.getProducts().add(savedProduct);
            // adds the updated category to the categoryList
            categoryList.add(category);
        }
        // iterates through category list of updated categories
        categoryRepo.saveAll(categoryList);
    }

@Transactional
    public void deleteProductById(Long id) throws Exception {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isEmpty())
            throw new Exception();

        Product product = productOptional.get();
        Iterable<Category> categoryIterable = categoryRepo.findAll();
        List<Category> categoryList = new ArrayList<>();

        for (Category category: categoryIterable) {
            categoryList.add(category);
        }

        for (Category cat: categoryList) {
            cat.getProducts().remove(product);
        }

        categoryRepo.saveAll(categoryList);
        productRepo.deleteById(id);
    }

    public void updateProduct(Long id, ProductDTO productRequest) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isEmpty())
            throw new Exception();

        Product product = productOptional.get();

        System.out.println(mapper.writeValueAsString(productRequest));

        if (productRequest.saleId != null) {
            Optional<Sale> saleOptional = saleRepo.findById(productRequest.saleId);
            if (saleOptional.isEmpty())
                throw new Exception();

            Sale sale = saleOptional.get();
            product.setSale(sale);
            System.out.println(mapper.writeValueAsString(product));
        }

        Set<Category> categorySet = new HashSet<>();

        for (CategoryDTO categoryDTO: productRequest.categories) {
            if (categoryDTO.id.isPresent()) {
                Optional<Category> categoryOptional = categoryRepo.findById(categoryDTO.id.get());
                if (categoryOptional.isEmpty())
                    throw new Exception();

                Category category = categoryOptional.get();
                categorySet.add(category);
            }
        }

        product.setProductName(productRequest.productName);
        product.setPrice(productRequest.price);
        product.setCategories(categorySet);
        product.setDescription(productRequest.description);
        product.setDiscontinued(productRequest.discontinued);
        product.setImage(productRequest.image);
        product.setAvailableDate(productRequest.availableDate);
        product.setQuantity(productRequest.quantity);
        product.setMinAdPrice(productRequest.minAdPrice);

        Product savedProduct = productRepo.save(product);

        if (savedProduct.getSale() != null) {
            Optional<Sale> saleOptional = saleRepo.findById(productRequest.saleId);
            if (saleOptional.isEmpty())
                throw new Exception();

            Sale sale = saleOptional.get();
            sale.getProducts().add(savedProduct);
            saleRepo.save(sale);
        }

        List<Category> categoryList = new ArrayList<>();

        for (Category categoryProduct: savedProduct.getCategories()) {
            Optional<Category> categoryOptional = categoryRepo.findById(categoryProduct.getId());
            Category category = categoryOptional.get();
            category.getProducts().add(savedProduct);
            categoryList.add(category);
        }
        categoryRepo.saveAll(categoryList);
    }
}
