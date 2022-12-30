package net.yorksolutions.fafoshop.repositories;

import net.yorksolutions.fafoshop.models.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleRepo extends CrudRepository<Sale, Long> {
    Optional<Sale> findSaleBySaleName(String saleName);
}
