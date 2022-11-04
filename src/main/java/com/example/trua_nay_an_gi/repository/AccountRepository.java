package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.app_users.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account,Long> {
    Account findByUserName(String name);
}
