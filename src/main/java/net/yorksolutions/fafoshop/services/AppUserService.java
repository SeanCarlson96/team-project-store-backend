package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.DTOs.AppUserDTO;
import net.yorksolutions.fafoshop.models.AppUser;
import net.yorksolutions.fafoshop.repositories.AppUserRepo;
import net.yorksolutions.fafoshop.repositories.CartRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepo appUserRepo;
    private final CartRepo cartRepo;

    public AppUserService(AppUserRepo appUserRepo, CartRepo cartRepo) {
        this.appUserRepo = appUserRepo;
        this.cartRepo = cartRepo;
    }

    public Iterable<AppUser> getAll() {
        return appUserRepo.findAll();
    }

    public AppUser getUserByEmailAndPassword(String email, String password) {
        return appUserRepo.findAppUserByEmailAndPassword(email, password).orElse(null);
    }

    public void createAppUser(AppUserDTO appUserRequest)  throws Exception{
        Optional<AppUser> appUserOptional = appUserRepo.findAppUserByEmail(appUserRequest.email);


        AppUser appUser = new AppUser();
        appUser.setEmail(appUserRequest.email);
        appUser.setPassword(appUserRequest.password);
        appUser.setUserType(appUserRequest.userType);
        appUserRepo.save(appUser);
    }

    public void deleteAppUserById(Long id) throws Exception {
        Optional<AppUser> appUserOptional = appUserRepo.findById(id);

        if (appUserOptional.isEmpty())
            throw new Exception();

        appUserRepo.deleteById(id);
    }

    public void updateAppUser(Long id, AppUserDTO appUserRequest) throws Exception {
        Optional<AppUser> appUserOptional = appUserRepo.findById(id);

        if (appUserOptional.isEmpty())
            throw new Exception();

        AppUser appUser = appUserOptional.get();
        appUser.setEmail(appUserRequest.email );
        appUser.setPassword(appUserRequest.password);
        appUser.setUserType(appUserRequest.userType);
        appUserRepo.save(appUser);
    }
}
