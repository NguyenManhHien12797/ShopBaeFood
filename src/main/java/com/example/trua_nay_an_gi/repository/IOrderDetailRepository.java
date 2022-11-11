package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.model.product.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long> {
//    Iterable<OrderDetail> findAllByOrder(Order order);
//
//    Iterable<OrderDetail> findAllByProductId(Long id);

    Iterable<OrderDetail> findAllByOrderId(Long id);

}
