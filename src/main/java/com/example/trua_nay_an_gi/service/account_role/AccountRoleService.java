package com.example.trua_nay_an_gi.service.account_role;

import com.example.trua_nay_an_gi.model.app_users.AccountRoleMap;
import com.example.trua_nay_an_gi.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AccountRoleService implements IAccountRole{
@Autowired
    AccountRoleRepository accountRoleRepository;
    @Override
    public Iterable<AccountRoleMap> findAll() {
        return accountRoleRepository.findAll();
    }

    @Override
    public Optional<AccountRoleMap> findById(Long id) {
        return accountRoleRepository.findById(id);
    }

    @Override
    public AccountRoleMap save(AccountRoleMap accountRoleMap) {
        return accountRoleRepository.save(accountRoleMap);
    }

    @Override
    public void remove(Long id) {
accountRoleRepository.deleteById(id);
    }
}
