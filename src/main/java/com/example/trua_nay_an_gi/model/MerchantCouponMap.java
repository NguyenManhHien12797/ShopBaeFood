package com.example.trua_nay_an_gi.model;

import com.example.trua_nay_an_gi.model.Coupon;
import com.example.trua_nay_an_gi.model.Merchant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "merchant_coupon")
public class MerchantCouponMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
}
