package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.model.product.OrderDetail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OderDetailRepository extends PagingAndSortingRepository<OrderDetail,Long> {
    Iterable<OrderDetail> findOrderDetailsByOrder(Order order);
}
