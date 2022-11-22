package com.example.trua_nay_an_gi.controller;


import com.example.trua_nay_an_gi.model.Coupon;
import com.example.trua_nay_an_gi.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@Controller
@RequestMapping("/api/coupons")
public class CouponController {
    @Autowired
    ICouponService couponService;

    @GetMapping
    public ResponseEntity<List<Coupon>> finAll() {
        List<Coupon> coupons = couponService.finallCouponDeleteflagTrue();
        if (coupons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coupon> updateCoupon (@RequestBody Coupon coupon,@PathVariable Long id ) {
        Optional<Coupon> coupons = couponService.findById(id);
        if (!coupons.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        coupon.setId(coupons.get().getId());
        return new ResponseEntity<>(couponService.save(coupon),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coupon> findById(@PathVariable Long id) {
        Optional<Coupon> couponOptional = couponService.findById(id);
        if (!couponOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(couponOptional.get(),HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Coupon> saveCoupon(@RequestBody  Coupon coupon) {
        return new ResponseEntity<>(couponService.save(coupon),HttpStatus.CREATED);
    }

    @PatchMapping("/merchant/{id}")
    public ResponseEntity<Coupon> disableCoupon (@PathVariable Long id ){
        Optional<Coupon> couponOptional = couponService.findById(id);
        if (!couponOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        couponOptional.get().setDeleteFlag(false);
        return new ResponseEntity<>(couponService.save(couponOptional.get()),HttpStatus.OK);
    }
}
