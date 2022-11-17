package com.example.trua_nay_an_gi.service.merchant;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.service.GeneralService;

import java.util.List;

public interface IMerchantService extends GeneralService<Merchant> {
//    Optional<Merchant> findMerchantByAccountId(Long id);
//
//    Optional<Merchant> findMerchantByAccount_Id(Long accountId);

//    Iterable<DishDto> getAllDishDTO(Long id);
//
//    Iterable<ICustomerDto> getAllCustomerDto (Long id);
//
//    Iterable<OrderByQueryDto> finAllMerchantOrderByCustomerId (Long merchantId, Long userId);
//
//    Iterable<OrderByQueryDto> finAllOrderByMerchantIdInPeriod (Long id, LocalDate startTime, LocalDate endTime);

    void saveMerchantToRegister(String address, String avatar, String name, String phone, String status, Long accountID);


    List<Merchant> findAllMerchantAndNameContai(String name);
}
