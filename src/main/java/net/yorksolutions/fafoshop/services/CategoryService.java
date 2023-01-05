package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.DTOs.CategoryDTO;
import net.yorksolutions.fafoshop.DTOs.ProductDTO;
import net.yorksolutions.fafoshop.models.Category;
import net.yorksolutions.fafoshop.models.Product;
import net.yorksolutions.fafoshop.repositories.CategoryRepo;
import net.yorksolutions.fafoshop.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    public CategoryService(CategoryRepo categoryRepo, ProductRepo productRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    public Iterable<Category> getAll() {
        return categoryRepo.findAll();
    }

    public Set<Product> getProductById(CategoryDTO productRequest) {
        Set<Product> products = new HashSet<>();

        for (ProductDTO categoryProducts : productRequest.products) {
            Category category = new Category();
            Optional<Product> product = productRepo.findById(categoryProducts.id.get());
            category.setProducts((Set<Product>) product.get());
        }
        return products;


    }

    public void createCategory(CategoryDTO categoryRequest) throws Exception {

        Category category = new Category();
        category.setCategoryName(categoryRequest.categoryName);


        category.setProducts((getProductById(categoryRequest)));

        categoryRepo.save(category);

        if (category.getProducts() != null) {
            for (Product categoryProduct : category.getProducts()) {
                Optional<Product> productOptional = productRepo.findById(categoryProduct.getId());
                if (productOptional.isEmpty()) throw new Exception("Product id does not exist");

                Product product = productOptional.get();
                product.getCategories().add(category);
                productRepo.save(product);
            }
        }
    }

    // Change name
    public void updateCategories(Long id, CategoryDTO categoryRequest) throws Exception {
        Optional<Category> currentCategory = categoryRepo.findById(id);

        if (currentCategory.isEmpty()) {
            throw new Exception("Category not found");
        }

        Category getCategory = currentCategory.get();

        getCategory.setCategoryName(categoryRequest.categoryName);
        getCategory.setProducts((getProductById(categoryRequest)));

        categoryRepo.save(getCategory);

    }

    public void deleteCategoryById(Long id) throws Exception {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        if (categoryOptional.isEmpty()) throw new Exception();

        categoryRepo.deleteById(id);
    }

    public Category getCategoryByCategoryName(String categoryName) {
        return categoryRepo.findCategoryByCategoryName(categoryName).orElse(null);
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }
}
