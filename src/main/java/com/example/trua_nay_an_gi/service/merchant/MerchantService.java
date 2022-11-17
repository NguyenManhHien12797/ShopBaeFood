package com.example.trua_nay_an_gi.service.merchant;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.repository.merchant.IMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantService implements IMerchantService {

    @Autowired
    private IMerchantRepository merchantRepository;

    @Override
    public Iterable<Merchant> findAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Optional<Merchant> findById(Long id) {
        return merchantRepository.findById(id);
    }

    @Override
    public Merchant save(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public void remove(Long id) {
        merchantRepository.deleteById(id);
    }

    @Override
    public void removeAll() {

    }

    @Override
    public void saveMerchantToRegister(String address, String avatar, String name, String phone, String status, Long accountID) {
        merchantRepository.saveMerchantToRegister(address, avatar, name, phone, status, accountID);
    }

    @Override
    public List<Merchant> findAllMerchantAndNameContai(String name) {
        return merchantRepository.findAllMerchantAndNameContai(name);
    }
//    @Override
//    public Optional<Merchant> findMerchantByAccountId(Long id) {
//        return merchantRepository.findMerchantByAccountId(id);
//    }
//
//    @Override
//    public Optional<Merchant> findMerchantByAccount_Id(Long accountId) {
//        return merchantRepository.findFirstByAccount_Id(accountId);
//    }

//    @Override
//    public Iterable<Merchant> findMerchantWithSameCategoryWith(Long merchantId, int limit) {
//        return merchantRepository.findMerchantWithSameCategoryWith(merchantId, limit);
//    }


//    @Override
//    public Iterable<Merchant> findMerchantByNameAndCategoryIdList(String namePattern, Long categoryIdList) {
//        String tempName = "%" + namePattern + "%";
//        return merchantRepository.findMerchantByNameAndCategoryIdList(tempName, categoryIdList);
//    }
}
