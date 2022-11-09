package com.example.trua_nay_an_gi.service.account_role;

import com.example.trua_nay_an_gi.model.app_users.AccountRoleMap;
import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import com.example.trua_nay_an_gi.service.GeneralService;

import java.util.Optional;

public interface IAccountRole extends GeneralService<AppRoles> {
    Optional<AppRoles> findByName(String name);
}
