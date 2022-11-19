package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.Merchant;

import java.util.List;

public interface IMerchantService extends IGeneralService<Merchant> {

    void saveMerchantToRegister(String address, String avatar, String name, String phone, String status, Long accountID);

    List<Merchant> findAllContainer(String name);

    Merchant findMerchantById(Long id);

    Merchant updateMerchant(Long id, Merchant merchant);

    void deleteMerchantById(Long id);
}
