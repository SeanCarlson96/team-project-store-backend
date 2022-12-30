package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.models.Category;
import net.yorksolutions.fafoshop.repositories.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Iterable<Category> getAll() {
        return categoryRepo.findAll();
    }


    public void createCategory(Category categoryRequest) throws Exception{
        Optional<Category> categoryOptional = categoryRepo.findCategoryByCategoryName( categoryRequest.getCategoryName());
            if (categoryOptional.isPresent())
                throw new Exception();

            Category category = new Category();
                category.setCategoryName(categoryRequest.getCategoryName());
                category.setProducts(categoryRequest.getProducts());

                categoryRepo.save(category);


    }

    public void deleteCategoryById(Long id) throws Exception {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
            if (categoryOptional.isEmpty())
                throw new Exception();

            categoryRepo.deleteById(id);
    }
}
