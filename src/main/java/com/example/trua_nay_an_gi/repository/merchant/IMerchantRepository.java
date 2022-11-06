package com.example.trua_nay_an_gi.repository.merchant;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMerchantRepository extends PagingAndSortingRepository<Merchant,Long> {
}
