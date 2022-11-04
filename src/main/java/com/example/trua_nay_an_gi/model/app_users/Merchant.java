package com.example.trua_nay_an_gi.model.app_users;

import com.example.trua_nay_an_gi.model.product.Category;
import com.example.trua_nay_an_gi.model.product.Coupon;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;

    private String address;
    private String avatar;
    private String imageBanner;
    private String openTime;
    private String closeTime;

    @OneToMany(mappedBy = "merchant")
    private Set<MerchantCategoryMap> merchantCategoryMapSet;

    @OneToMany(mappedBy = "merchant")
    private Set<MerchantCouponMap> merchantCouponMaps;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

}
