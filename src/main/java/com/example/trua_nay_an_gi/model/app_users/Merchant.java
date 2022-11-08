package com.example.trua_nay_an_gi.model.app_users;

import com.example.trua_nay_an_gi.model.product.Category;
import com.example.trua_nay_an_gi.model.product.Coupon;
import com.example.trua_nay_an_gi.model.product.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
//    @NotEmpty
    private String name;
//    @NotEmpty
//    @Pattern(regexp = "^[0](\\+\\d{1,3}\\s?)?((\\(\\d{3}\\)\\s?)|(\\d{3})(\\s|-?))(\\d{3}(\\s|-?))(\\d{3})(\\s?(([E|e]xt[:|.|]?)|x|X)(\\s?\\d+))?")
    private String phone;

//    @NotEmpty
    private String address;

    private String avatar;
    private String imageBanner;

//    @Column(columnDefinition = "TIME")
    private String openTime;

//    @Column(columnDefinition = "TIME")
    private String closeTime;

    private String status;

    @OneToMany(mappedBy = "merchant")
    private List<Product> productList;

    @OneToMany(mappedBy = "merchant")
    private Set<MerchantCategoryMap>merchantCategoryMaps;


    @OneToMany(mappedBy = "merchant")
    private Set<MerchantCouponMap> merchantCouponMaps;

//    @Column(name = "isActive", columnDefinition = "boolean default true")
//    private boolean isActive;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;
}
