package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    @Query(value = "select * from Product as p where p.delete_flag = true and p.merchant_id = ?1", nativeQuery = true)
    Iterable<Product> getAllByDeleteFlagTrueAndMerchant(Long id);
    @Query(value = "select * from Product as p where p.delete_flag = true", nativeQuery = true)
    List<Product> finallProductdeleteflagTrue();

    @Query(value = "select * from Product p where p.delete_flag = true and p.merchant_id = ?1 and p.name like concat('%', ?2, '%')", nativeQuery = true)
    List<Product> fAllByDeleFlagTAndMerAndNameContai(Long id,String name);

    Optional<Product> findProductById(Long id);
}
