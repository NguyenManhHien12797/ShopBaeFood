package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.app_users.AccountRoleMap;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends PagingAndSortingRepository<AccountRoleMap,Long> {
}
