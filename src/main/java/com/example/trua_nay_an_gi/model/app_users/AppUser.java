package com.example.trua_nay_an_gi.model.app_users;

import com.example.trua_nay_an_gi.model.product.Cart;

import java.util.Set;

public class AppUser {
    private Long id;
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String avatarUrl;
    private Set<AppRoles> appRoles;
    private Cart cart;
    private Status status;

}
