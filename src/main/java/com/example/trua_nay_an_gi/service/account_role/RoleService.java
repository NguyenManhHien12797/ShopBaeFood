package com.example.trua_nay_an_gi.service.account_role;

import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import com.example.trua_nay_an_gi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService implements IRoleService {
@Autowired
RoleRepository accountRoleRepository;


    @Override
    public Iterable<AppRoles> findAll() {
        return accountRoleRepository.findAll();
    }

    @Override
    public Optional<AppRoles> findById(Long id) {
        return accountRoleRepository.findById(id);
    }

    @Override
    public AppRoles save(AppRoles appRoles) {
        return accountRoleRepository.save(appRoles);
    }

    @Override
    public void remove(Long id) {
        accountRoleRepository.deleteById(id);
    }

    @Override
    public Optional<AppRoles> findByName(String name) {
        return accountRoleRepository.findByName(name);
    }

    @Override
    public void setDefaultRole(Long accountId, Integer roleId) {
        accountRoleRepository.setDefaultRole(accountId,roleId);
    }


}
