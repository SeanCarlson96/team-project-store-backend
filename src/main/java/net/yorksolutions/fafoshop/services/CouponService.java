package net.yorksolutions.fafoshop.services;

import net.yorksolutions.fafoshop.models.AppUser;
import net.yorksolutions.fafoshop.models.Coupon;
import net.yorksolutions.fafoshop.repositories.AppUserRepo;
import net.yorksolutions.fafoshop.repositories.CouponRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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

    public void createCoupon(Coupon couponRequest) throws Exception {
        Optional<Coupon> couponOpt = couponRepo.findCouponByCouponName(couponRequest.getCouponName());
        if (couponOpt.isPresent())
            throw new Exception();

        Coupon coupon = new Coupon();
        coupon.setCouponName(couponRequest.getCouponName());
        coupon.setStartDate(couponRequest.getStartDate());
        coupon.setStopDate(couponRequest.getStopDate());
        coupon.setUseLimit(couponRequest.getUseLimit());
        coupon.setPercentage(couponRequest.getPercentage());
        

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

    public void updateCoupon(Long id, Coupon couponRequest) throws Exception {
        Optional<Coupon> couponOpt = this.couponRepo.findById(id);
        if (couponOpt.isEmpty()) {
            throw new Exception();
        }
        Coupon coupon = couponOpt.get();
        //coupon.setUser(couponRequest.getUser());
        coupon.setCouponName(couponRequest.getCouponName());
        coupon.setStartDate(couponRequest.getStartDate());
        coupon.setStopDate(couponRequest.getStopDate());
        coupon.setPercentage(couponRequest.getPercentage());
        coupon.setUseLimit(couponRequest.getUseLimit());
        couponRepo.save(coupon);
    }
}
