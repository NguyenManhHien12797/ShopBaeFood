package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.model.Order;

import java.util.Optional;

public interface IOrderService extends IGeneralService<Order> {
    Iterable<Order>findOrdersByAppUser(Long user_id);
    Order findOrderById(Long id);
    Order updateOrder(Long id, Order order);

}
