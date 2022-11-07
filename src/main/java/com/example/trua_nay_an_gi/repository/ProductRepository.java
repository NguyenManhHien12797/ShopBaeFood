package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.product.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
}
