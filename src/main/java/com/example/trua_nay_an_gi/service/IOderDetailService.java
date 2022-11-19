package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.Order;
import com.example.trua_nay_an_gi.model.OrderDetail;

public interface IOderDetailService extends IGeneralService<OrderDetail> {
    Iterable<OrderDetail> findOrderDetailsByOrder(Order order);
}
