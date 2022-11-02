package com.example.trua_nay_an_gi.model.product;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.app_users.Merchant;

public class Coupon {
    private Long id;
    private String name;
    private Merchant merchant;
    private String type;
    private double value;
}
