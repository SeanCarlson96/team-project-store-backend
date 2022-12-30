package net.yorksolutions.fafoshop.models;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String couponName;
    public Date startDate;
    public Date stopDate;
    public Integer useLimit;
    public Double percentage;
    @ManyToOne
    public AppUser user;

    public Coupon(Long id, String couponName, Date startDate, Date stopDate, Integer useLimit, Double percentage, AppUser user) {
        this.id = id;
        this.couponName = couponName;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.useLimit = useLimit;
        this.percentage = percentage;
        this.user = user;
    }
    public Coupon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public Integer getUseLimit() {
        return useLimit;
    }

    public void setUseLimit(Integer useLimit) {
        this.useLimit = useLimit;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
