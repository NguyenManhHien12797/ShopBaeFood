package com.example.trua_nay_an_gi.service.seviceImpl;


import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.model.Order;
import com.example.trua_nay_an_gi.repository.IOrderRepository;

import com.example.trua_nay_an_gi.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IOrderRepository orderRepository;
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
        return orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void removeAll() {

    }

    @Override
    public Iterable<Order> findOrdersByAppUser(AppUser user) {
        return orderRepository.findOrdersByAppUser(user);
    }
}
