package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    @Query(value = "select * from Product as p where p.delete_flag = true and p.merchant_id = ?1", nativeQuery = true)
    Iterable<Product> getAllByDeleteFlagTrueAndMerchant(Long id);
}
