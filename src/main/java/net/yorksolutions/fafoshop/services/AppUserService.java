package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.models.AppUser;
import net.yorksolutions.fafoshop.repositories.AppUserRepo;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final AppUserRepo appUserRepo;

    public AppUserService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    public Iterable<AppUser> getAll() {
        return appUserRepo.findAll();

    }

    public AppUser getUserByEmailAndPassword(String email, String password) {
        return appUserRepo.findAppUserByEmailAndPassword(email, password).orElse(null);

    }
}
