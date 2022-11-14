package com.example.trua_nay_an_gi.service.account;

import com.example.trua_nay_an_gi.model.app_users.Account;
import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.model.dto.MerchantDTO;
import com.example.trua_nay_an_gi.service.GeneralService;

import java.util.Optional;
import java.util.Set;

public interface IAccountService extends GeneralService<Account> {
    Account findByName(String name);
    Long findIdUserByUserName(String username);

    boolean existsAccountByUserName(String username);
    Set<AppRoles> findAppRoleByAccountId(Long id);
    Optional<Account> findAccByMerchantId(Long id);

    String findEmailByMerchantID(Long id);

}
