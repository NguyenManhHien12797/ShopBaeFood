package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.Order;
import com.example.trua_nay_an_gi.model.OrderDetail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOderDetailRepository extends PagingAndSortingRepository<OrderDetail,Long> {
    Iterable<OrderDetail> findOrderDetailsByOrder(Order order);

    Optional<OrderDetail> findOrderDetailById(Long id);
}
