package com.example.trua_nay_an_gi.model.dto;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import lombok.Builder;

import java.util.List;

@Builder
public class AccountToken {
    private long id;
    private String username;
    private String token;

    private List<String> roles;

    private Merchant merchant;

    public AccountToken(long id, String username, String token, List<String> roles, Merchant merchant) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.roles = roles;
        this.merchant = merchant;
    }

    public AccountToken() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
