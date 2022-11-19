package com.example.trua_nay_an_gi.service.seviceImpl;

import com.example.trua_nay_an_gi.model.Coupon;
import com.example.trua_nay_an_gi.repository.ICouponRepository;
import com.example.trua_nay_an_gi.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements ICouponService {
    @Autowired
    ICouponRepository couponRepository;
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

    @Override
    public List<Coupon> finallCouponDeleteflagTrue() {
        return couponRepository.finallCouponDeleteflagTrue();
    }
}
