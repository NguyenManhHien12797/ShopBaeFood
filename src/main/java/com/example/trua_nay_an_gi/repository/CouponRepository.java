package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.product.Coupon;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends PagingAndSortingRepository<Coupon,Long> {

}
