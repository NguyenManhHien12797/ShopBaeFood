package com.example.trua_nay_an_gi.service.coupon;

import com.example.trua_nay_an_gi.model.product.Coupon;
import com.example.trua_nay_an_gi.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CouponService implements ICouponService {
    @Autowired
    CouponRepository couponRepository;
    @Override
    public Iterable<Coupon> findAll() {
        return couponRepository.findAll();
    }

    @Override
    public Optional<Coupon> findById(Long id) {
        return couponRepository.findById(id);
    }

    @Override
    public Coupon save(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public void remove(Long id) {
        couponRepository.deleteById(id);
    }
}
