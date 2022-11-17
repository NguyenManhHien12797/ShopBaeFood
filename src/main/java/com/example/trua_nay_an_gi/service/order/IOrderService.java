package com.example.trua_nay_an_gi.service.order;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.service.GeneralService;

import java.util.List;

public interface IOrderService extends GeneralService<Order> {
    Iterable<Order>findOrdersByAppUser(AppUser user);
}
