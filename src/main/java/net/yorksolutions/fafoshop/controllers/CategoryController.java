package net.yorksolutions.fafoshop.controllers;

import net.yorksolutions.fafoshop.models.Category;
import net.yorksolutions.fafoshop.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Category> getAll(){return service.getAll(); }

    @PostMapping
    public void createCategory(
            @RequestBody Category category
    ){
        try {
            service.createCategory(category);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(
            @PathVariable Long id
    ) {
        try {
            service.deleteCategoryById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
