package com.example.trua_nay_an_gi.service.order_detail;

import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.model.product.OrderDetail;
import com.example.trua_nay_an_gi.service.GeneralService;

public interface IOrderDetailService extends GeneralService<OrderDetail> {
//    Iterable<OrderDetail> findAllByOrder(Order order);
//
//    Iterable<OrderDetail> findAllByProductId(Long id);
//
//    Iterable<Order> findAllOrderByProductId(Long id);

    Iterable<OrderDetail> findAllByOrderId(Long id);

}
