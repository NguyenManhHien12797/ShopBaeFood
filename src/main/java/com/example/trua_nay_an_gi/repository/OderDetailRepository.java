package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.product.OrderDetail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OderDetailRepository extends PagingAndSortingRepository<OrderDetail,Long> {
}
