package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.models.Category;
import net.yorksolutions.fafoshop.models.Product;
import net.yorksolutions.fafoshop.repositories.CategoryRepo;
import net.yorksolutions.fafoshop.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    public void createCategory(Category categoryRequest) throws Exception{
        if (categoryRepo.findCategoryByCategoryName(categoryRequest.getCategoryName()).isPresent())
            throw new Exception();

        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setProducts(categoryRequest.getProducts());

        categoryRepo.save(category);

        if (category.getProducts() != null) {
            for (Product categoryProduct: category.getProducts()) {
                Optional<Product> productOptional = productRepo.findById(categoryProduct.getId());
                if (productOptional.isEmpty())
                    throw new Exception("Product id does not exist");

                Product product = productOptional.get();
                product.getCategories().add(category);
                productRepo.save(product);
            }
        }
    }

    public void deleteCategoryById(Long id) throws Exception {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
            if (categoryOptional.isEmpty())
                throw new Exception();

            categoryRepo.deleteById(id);
    }

    public Category getCategoryByCategoryName(String categoryName) {
        return categoryRepo.findCategoryByCategoryName(categoryName).orElse(null);
    }
}