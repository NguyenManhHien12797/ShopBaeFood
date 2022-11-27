package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.Order;
import com.example.trua_nay_an_gi.model.OrderDetail;

import java.util.Optional;

public interface IOderDetailService extends IGeneralService<OrderDetail> {
    Iterable<OrderDetail> findOrderDetailsByOrderId(Long id);
    OrderDetail findOrderDetailById(Long id);

    OrderDetail updateOrderdetal(Long id, OrderDetail orderDetail);
}
