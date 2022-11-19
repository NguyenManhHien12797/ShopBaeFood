package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.AppUser;

public interface IAppUserSevice extends IGeneralService<AppUser> {
    boolean existByName(String name);

    AppUser findByUserName(String name);

    void saveUserToRegister(String address, String avatar, String name, String phone, Long account_id, String status);
}
