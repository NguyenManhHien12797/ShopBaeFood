package com.example.trua_nay_an_gi.service.seviceImpl;


import com.example.trua_nay_an_gi.exception.AccountNotFoundException;
import com.example.trua_nay_an_gi.exception.OrderNotFoundException;
import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.model.Order;
import com.example.trua_nay_an_gi.repository.IOrderRepository;

import com.example.trua_nay_an_gi.service.IAppUserSevice;
import com.example.trua_nay_an_gi.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IOrderRepository orderRepository;
    @Autowired
    private IAppUserSevice userSevice;
    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        order.setOrderdate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }


    @Override
    public Iterable<Order> findOrdersByAppUser(Long user_id) {
        AppUser appUser = userSevice.findUserById(user_id);
        return orderRepository.findOrdersByAppUser(appUser);
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findOrderById(id).orElseThrow(() -> new OrderNotFoundException(404, "Order by id "+ id + " was not found"));
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        Order updateOrder = this.findOrderById(id);
        order.setId(updateOrder.getId());
        order.setAppUser(updateOrder.getAppUser());
        order.setOrderdate(LocalDateTime.now());
        return orderRepository.save(order);
    }
}
