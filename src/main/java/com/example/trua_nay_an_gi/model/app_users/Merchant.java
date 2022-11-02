package com.example.trua_nay_an_gi.model.app_users;

import com.example.trua_nay_an_gi.model.product.Category;

import java.util.List;

public class Merchant {
    private Long id;
    private String name;
    private String phone;
    private List<Category> category;
    private String address;
    private String avatar;
    private String imageBanner;
    private String openTime;
    private String closeTime;
    private Status status;

}
