package net.yorksolutions.fafoshop.controllers;

import net.yorksolutions.fafoshop.models.Category;
import net.yorksolutions.fafoshop.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Category> getAll() {
        return service.getAll();
    }

    @GetMapping(params = {"categoryName"})
    public Category getCategoryByCategoryName(
            @RequestParam String categoryName
    ) {
        try {
            return service.getCategoryByCategoryName(categoryName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = {"id"})
    public Category getCategory(@PathVariable Long id){
        try {
            return service.getCategoryById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public void createCategory(
            @RequestBody Category category
    ) {
        try {
            service.createCategory(category);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(
            @PathVariable Long id
    ) {
        try {
            service.deleteCategoryById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public void updateCategory(@RequestParam Long id, @RequestBody Category category){
        try{
            service.updateCategories(id,category);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
