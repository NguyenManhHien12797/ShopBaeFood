package com.example.trua_nay_an_gi.service.coupon;

import com.example.trua_nay_an_gi.model.product.Coupon;
import com.example.trua_nay_an_gi.service.GeneralService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICouponService extends GeneralService<Coupon> {
    List<Coupon> finallCouponDeleteflagTrue();
}
