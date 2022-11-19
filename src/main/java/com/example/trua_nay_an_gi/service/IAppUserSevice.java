package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.AppUser;

public interface IAppUserSevice extends IGeneralService<AppUser> {

    AppUser findUserById(Long id);
    AppUser updateUser(Long id, AppUser user);


    void saveUserToRegister(String address, String avatar, String name, String phone, Long account_id, String status);
}
