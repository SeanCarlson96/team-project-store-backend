package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.DTOs.CouponDTO;
import net.yorksolutions.fafoshop.models.AppUser;
import net.yorksolutions.fafoshop.models.Coupon;
import net.yorksolutions.fafoshop.repositories.AppUserRepo;
import net.yorksolutions.fafoshop.repositories.CouponRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CouponService {
    private final CouponRepo couponRepo;
    private final AppUserRepo appUserRepo;
    public CouponService(CouponRepo couponRepo, AppUserRepo appUserRepo) {
        this.couponRepo = couponRepo;
        this.appUserRepo = appUserRepo;
    }
    public Coupon getCouponByName(String couponName) {
        return couponRepo.findCouponByCouponName(couponName).orElse(null);
    }

    public void createCoupon(CouponDTO couponRequest) throws Exception {
        Optional<Coupon> couponOpt = couponRepo.findCouponByCouponName(couponRequest.couponName);
        if (couponOpt.isPresent())
            throw new Exception();

        Coupon coupon = new Coupon();
        coupon.setCouponName(couponRequest.couponName);
        coupon.setStartDate(couponRequest.startDate);
        coupon.setStopDate(couponRequest.stopDate);
        coupon.setUseLimit(couponRequest.useLimit);
        coupon.setPercentage(couponRequest.percentage);
        

        couponRepo.save(coupon);
    }

    public Iterable<Coupon> getAllCoupons() {
        return couponRepo.findAll();
    }

    public void deleteCouponById(Long id) throws Exception {
        Optional<Coupon> couponOpt = couponRepo.findById(id);
        if (couponOpt.isEmpty())
            throw new Exception();

        couponRepo.deleteById(id);
    }

    public void updateCoupon(Long id, CouponDTO couponRequest) throws Exception {
        Optional<Coupon> couponOpt = this.couponRepo.findById(id);
        if (couponOpt.isEmpty()) {
            throw new Exception();
        }
        Optional<AppUser> userOpt = appUserRepo.findById(couponRequest.userId.id.get());

        Coupon coupon = couponOpt.get();
        coupon.setCouponName(couponRequest.couponName);
        coupon.setStartDate(couponRequest.startDate);
        coupon.setStopDate(couponRequest.stopDate);
        coupon.setPercentage(couponRequest.percentage);
        coupon.setUseLimit(couponRequest.useLimit);


        AppUser couponUser = userOpt.get();
        coupon.setUser(couponUser);
        couponRepo.save(coupon);
    }


}
