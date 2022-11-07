package com.example.trua_nay_an_gi.service.merchant;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.service.GeneralService;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.Optional;

public interface IMerchantService extends GeneralService<Merchant> {
    Optional<Merchant> findMerchantByAccountId(Long id);

    Optional<Merchant> findMerchantByAccount_Id(Long accountId);

//    Iterable<DishDto> getAllDishDTO(Long id);
//
//    Iterable<ICustomerDto> getAllCustomerDto (Long id);
//
//    Iterable<OrderByQueryDto> finAllMerchantOrderByCustomerId (Long merchantId, Long userId);
//
//    Iterable<OrderByQueryDto> finAllOrderByMerchantIdInPeriod (Long id, LocalDate startTime, LocalDate endTime);
=======
public interface IMerchantService extends GeneralService<Merchant> {
>>>>>>> dev
}
