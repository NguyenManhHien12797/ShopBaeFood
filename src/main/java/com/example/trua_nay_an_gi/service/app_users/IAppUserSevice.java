package com.example.trua_nay_an_gi.service.app_users;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.service.GeneralService;

public interface IAppUserSevice extends GeneralService<AppUser> {
boolean existByName(String name);
AppUser findByUserName(String name);
}
