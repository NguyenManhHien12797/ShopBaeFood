package com.example.trua_nay_an_gi.repository.merchant;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.model.product.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

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

    @Query(value = "select * from merchant limit :limit", nativeQuery = true)
    Iterable<Merchant> findAllMerchant(@Param(value = "limit") int limit);

    @Query(value = "select * from merchant where name like :namePattern limit :limit", nativeQuery = true)
    Iterable<Merchant> findAllMerchantWithName(@Param(value = "namePattern") String namePattern, @Param(value = "limit") int limit);

    @Query(value = "select merchant.* from merchant join merchant_category mc on merchant.id = mc.merchant_id " +
            "where category_id in (:categoryIdList) limit :limit offset 0", nativeQuery = true)
    Iterable<Merchant> findMerchantByCategoryIdList(
            @Param(value = "categoryIdList") String categoryIdList,
            @Param(value = "limit") int limit);

    @Query(value = "select merchant.* from merchant join merchant_category mc on merchant.id = mc.merchant_id " +
            "where category_id in (:categoryIdList) and merchant.name like :namePattern  limit :limit offset 0", nativeQuery = true)
    Iterable<Merchant> findMerchantByNameAndCategoryIdList(
            @Param(value = "namePattern") String namePattern, @Param(value = "categoryIdList") String categoryIdList,
            @Param(value = "limit") int limit);

    @Query(value = "select distinct merchant.* " +
            "from merchant join merchant_category m on merchant.id = m.merchant_id " +
            "where m.category_id in ( " +
            "    select mc.category_id " +
            "    from merchant_category mc " +
            "    where mc.merchant_id = :merchantId) " +
            "and merchant.id != :merchantId " +
            "order by merchant.sold desc " +
            "limit :limit", nativeQuery = true)
    Iterable<Merchant> findMerchantWithSameCategoryWith(@Param(value = "merchantId") Long merchantId, @Param(value = "limit") int limit);
}
