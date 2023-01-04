package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.DTOs.AppUserDTO;
import net.yorksolutions.fafoshop.DTOs.CouponDTO;
import net.yorksolutions.fafoshop.models.AppUser;
import net.yorksolutions.fafoshop.models.Coupon;
import net.yorksolutions.fafoshop.repositories.AppUserRepo;
import net.yorksolutions.fafoshop.repositories.CouponRepo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserService {

    private final AppUserRepo appUserRepo;
    private final CouponRepo couponRepo;
    public AppUserService(AppUserRepo appUserRepo, CouponRepo couponRepo) {
        this.appUserRepo = appUserRepo;
        this.couponRepo = couponRepo;
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
        appUser.setEmail(appUserRequest.email);
        appUser.setPassword(appUserRequest.password);
        appUser.setUserType(appUserRequest.userType);

        Set<Coupon> couponSet = new HashSet<>();
        for (CouponDTO couponDTO: appUserRequest.coupons) {
            //Coupon coupon = new Coupon();
            Optional<Coupon> couponOpt = couponRepo.findCouponByCouponName(couponDTO.couponName);

            Coupon c = couponOpt.get();
            couponSet.add(c);
        }
        couponRepo.saveAll(couponSet);

        appUserRepo.save(appUser);
    }
}
