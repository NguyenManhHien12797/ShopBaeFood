package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMerchantRepository extends PagingAndSortingRepository<Merchant,Long> {
    Optional<Merchant> findMerchantByAccountId(Long id);

    Optional<Merchant> findFirstByAccount_Id(Long accountId);
}
