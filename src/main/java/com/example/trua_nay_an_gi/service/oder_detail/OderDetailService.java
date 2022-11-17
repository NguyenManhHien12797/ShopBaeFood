package com.example.trua_nay_an_gi.service.oder_detail;


import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.model.product.OrderDetail;
import com.example.trua_nay_an_gi.repository.OderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OderDetailService implements IOderDetailService {
    @Autowired
    OderDetailRepository oderDetailRepository;
    @Override
    public Iterable<OrderDetail> findAll() {
        return oderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return oderDetailRepository.findById(id);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return oderDetailRepository.save(orderDetail);
    }

    @Override
    public void remove(Long id) {
        oderDetailRepository.deleteById(id);
    }

    @Override
    public void removeAll() {

    }


    @Override
    public Iterable<OrderDetail> findOrderDetailsByOrder(Order order) {
        return oderDetailRepository.findOrderDetailsByOrder(order);
    }
}
