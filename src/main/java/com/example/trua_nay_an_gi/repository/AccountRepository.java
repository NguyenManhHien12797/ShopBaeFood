package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.app_users.Account;
import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account,Long> {
    @Query("select a from account a where a.userName = ?1")
    Account findByUserName(String name);

    @Query(nativeQuery = true, value = "Select r.id,r.name FROM account as a JOIN account_role as ar ON a.id = ar.account_id JOIN app_roles as r on ar.role_id = r.id where a.id = ?1")
    Collection<GrantedAuthority> findRoleByAccountId(Long id);
    @Query(nativeQuery = true, value = "Select r.name FROM account as a JOIN account_role as ar ON a.id = ar.account_id JOIN app_roles as r on ar.role_id = r.id where a.id = ?1")
    List<String> findAppRoleByAccountId(Long id);

}
