package com.example.trua_nay_an_gi.model.app_users;

import com.example.trua_nay_an_gi.model.product.Coupon;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
