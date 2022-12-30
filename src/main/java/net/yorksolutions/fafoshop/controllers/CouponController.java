package net.yorksolutions.fafoshop.controllers;

import net.yorksolutions.fafoshop.models.Coupon;
import net.yorksolutions.fafoshop.services.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/coupons")
@CrossOrigin
public class CouponController {
    private final CouponService service;
    public CouponController(CouponService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Coupon> getAll() { return service.getAllCoupons();}

    @GetMapping(params = {"couponName"})
    public Coupon getCouponByName(@RequestParam String couponName) {
        try {
            return service.getCouponByName(couponName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void createCoupon(@RequestBody Coupon coupon) {
        try{
            service.createCoupon(coupon);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCouponById(@PathVariable Long id) {
        try {
            service.deleteCouponById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void updateCoupon(@PathVariable Long id, @RequestBody Coupon coupon) {
        try {
            service.updateCoupon(id, coupon);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }



}
