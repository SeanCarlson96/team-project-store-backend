package net.yorksolutions.fafoshop.DTOs;

import java.util.Date;
import java.util.Optional;

public class CouponDTO {
    public Optional<Long> id;
    public String couponName;
    public Date startDate;
    public Date stopDate;
    public Integer useLimit;
    public Double percentage;
    public AppUserDTO userId;
}
