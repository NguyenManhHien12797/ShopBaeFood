package com.example.trua_nay_an_gi.service.order_detail;

import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.model.product.OrderDetail;
import com.example.trua_nay_an_gi.repository.IOrderDetailRepository;
import com.example.trua_nay_an_gi.repository.IOrderRepository;
import com.example.trua_nay_an_gi.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Override
    public Iterable<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void remove(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public Iterable<OrderDetail> findAllByOrderId(Long id) {
        return orderDetailRepository.findAllByOrderId(id);
    }
}
