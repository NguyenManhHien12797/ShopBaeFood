package com.example.trua_nay_an_gi.model.app_users;

import com.example.trua_nay_an_gi.model.product.Coupon;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "user_coupon")
public class UserCouponMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private AppUser User;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    @JsonBackReference
    private Coupon coupon;
}
