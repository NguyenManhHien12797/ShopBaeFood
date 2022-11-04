package com.example.trua_nay_an_gi.model.product;

import com.example.trua_nay_an_gi.model.app_users.MerchantCouponMap;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private double value;
    @OneToMany(mappedBy = "coupon")
    @JsonBackReference
    private Set<MerchantCouponMap> merchantCouponMapSet;
}
