package net.yorksolutions.fafoshop.repositories;

import net.yorksolutions.fafoshop.models.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends CrudRepository<AppUser, Long>  {

    Optional<AppUser> findAppUserByEmailAndPassword(String email, String password);


}
