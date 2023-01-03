package net.yorksolutions.fafoshop.repositories;

import net.yorksolutions.fafoshop.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {

    Optional<Category> findCategoryByCategoryName(String categoryName);
}
