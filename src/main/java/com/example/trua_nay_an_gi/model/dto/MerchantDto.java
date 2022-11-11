package com.example.trua_nay_an_gi.model.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class MerchantDto {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String url;
    private String registrationCertificate;
    private String taxIdentificationNumber;
    private String representative;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Float rate;
    private Integer quantityRate;
//    private Constant.MerchantStatus status;
//    private Constant.ChannelName channel;
//    private Set<MerchantImage> merchantImages;
//    private Collection<CouponDetailDTO> couponDetails = new ArrayList<>();
}
