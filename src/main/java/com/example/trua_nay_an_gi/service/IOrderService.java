package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.model.Order;

import java.util.Optional;

public interface IOrderService extends IGeneralService<Order> {
    Iterable<Order>findOrdersByAppUser(AppUser user);
    Order findOrderById(Long id);
}
