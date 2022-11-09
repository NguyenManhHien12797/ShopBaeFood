package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.app_users.AccountRoleMap;
import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRoleRepository extends PagingAndSortingRepository<AppRoles,Long> {
    Optional<AppRoles> findByName(String name);
}
