package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.AppRoles;

import java.util.Optional;

public interface IRoleService extends IGeneralService<AppRoles> {
    Optional<AppRoles> findByName(String name);
    void setDefaultRole(Long accountId, Integer roleId);
}
