package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.Coupon;

import java.util.List;


public interface ICouponService extends IGeneralService<Coupon> {
    List<Coupon> finallCouponDeleteflagTrue();
}
