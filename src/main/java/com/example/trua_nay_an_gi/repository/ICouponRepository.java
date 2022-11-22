package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.Coupon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICouponRepository extends PagingAndSortingRepository<Coupon,Long> {
    @Query(value = "select * from Coupon as p where p.delete_flag = true", nativeQuery = true)
    List<Coupon> finallCouponDeleteflagTrue();
}
