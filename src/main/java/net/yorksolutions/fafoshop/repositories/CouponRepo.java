package net.yorksolutions.fafoshop.repositories;

import net.yorksolutions.fafoshop.models.Coupon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepo extends CrudRepository<Coupon, Long> {
    Optional<Coupon> findCouponByCouponName(String couponName);
}
