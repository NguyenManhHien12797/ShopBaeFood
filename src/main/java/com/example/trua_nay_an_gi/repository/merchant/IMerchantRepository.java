package com.example.trua_nay_an_gi.repository.merchant;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IMerchantRepository extends PagingAndSortingRepository<Merchant,Long> {
//    Optional<Merchant> findMerchantByAccountId(Long id);
//
//    Optional<Merchant> findFirstByAccount_Id(Long accountId);

    @Modifying
    @Transactional
    @Query(value = "insert into merchant(address,avatar,name,phone,status,account_id) values (?1,?2,?3,?4,?5,?6);", nativeQuery = true)
    void saveMerchantToRegister(String address, String avatar, String name, String phone, String status, Long accountID);

    @Query(value = "select * from merchant m where m.name like concat('%', ?1, '%')", nativeQuery = true)
    List<Merchant> findAllMerchantAndNameContai(String name);
}
