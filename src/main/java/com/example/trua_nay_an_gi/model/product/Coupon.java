package com.example.trua_nay_an_gi.model.product;

import com.example.trua_nay_an_gi.model.app_users.MerchantCouponMap;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    public static final String FLAT = "FLAT";
    public static final String PERCENT = "PERCENT";

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
