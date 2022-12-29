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
}
