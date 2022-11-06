package com.example.trua_nay_an_gi.service.account;

import com.example.trua_nay_an_gi.model.app_users.Account;
import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import com.example.trua_nay_an_gi.service.GeneralService;

import java.util.Set;

public interface IAccountService extends GeneralService<Account> {
    Account findByName(String name);
    Set<AppRoles> findAppRoleByAccountId(Long id);

}
