package com.example.trua_nay_an_gi.service.merchant;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.service.GeneralService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMerchantService extends GeneralService<Merchant> {

    void saveMerchantToRegister(String address, String avatar, String name, String phone, String status, Long accountID);

    List<Merchant> findAllContainer(String name);

    Merchant findMerchantById(Long id);

    Merchant updateMerchant(Long id, Merchant merchant);

    void deleteMerchantById(Long id);
}
