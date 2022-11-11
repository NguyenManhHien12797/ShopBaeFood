package com.example.trua_nay_an_gi.service.order;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.model.dto.cart.CartDetailDto;
import com.example.trua_nay_an_gi.model.dto.cart.CartDto;
import com.example.trua_nay_an_gi.model.dto.order.OrderDto;
import com.example.trua_nay_an_gi.model.dto.order.OrderDtoByOwner;
import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.model.product.OrderDetail;
import com.example.trua_nay_an_gi.repository.IOrderRepository;
import com.example.trua_nay_an_gi.service.order_detail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

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
    public Iterable<Order> findAllByUserId(Long id) {
        return orderRepository.findAllByUserId(id);
    }

    @Override
    public Iterable<Order> findAllByMerchantId(Long id) {
        return orderRepository.findAllByMerchantId(id);
    }

    @Override
    public Iterable<Order> findByStatus(int status,Long id) {
        return orderRepository.findAllByStatusAndMerchantId(status,id);
    }
}
