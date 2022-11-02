package com.example.trua_nay_an_gi.model.product;

import com.example.trua_nay_an_gi.model.app_users.AppUser;

import java.util.List;

public class Cart {
    private Long id;
    private AppUser user;
    private List<Product> products;
    private int quantity;
    private Double totalPrice;

}
