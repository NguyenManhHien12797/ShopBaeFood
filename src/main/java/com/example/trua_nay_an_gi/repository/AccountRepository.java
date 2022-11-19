package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.app_users.Account;
import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.model.dto.MerchantDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account,Long> {
    @Query("select a from account a where a.userName = ?1")
    Account findByUserName(String name);

    boolean existsAccountByUserName(String username);

    @Query(value = "select id from  account where user_name = ?1", nativeQuery = true)
    Long findIdUserByUserName(String username);

    @Query(nativeQuery = true, value = "select * from account join merchant m on account.id = m.account_id and m.id =?1")
    Optional<Account> findAccByMerchantId(Long id);
    @Query(nativeQuery = true, value = "select * from account join app_user a on account.id = a.account_id and a.id =?1")
    Optional<Account> findAccByUserId(Long id);
    Optional<Account> findAccountById(Long id);
}
