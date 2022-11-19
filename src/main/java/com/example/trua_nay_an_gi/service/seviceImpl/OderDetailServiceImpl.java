package com.example.trua_nay_an_gi.service.seviceImpl;


import com.example.trua_nay_an_gi.model.Order;
import com.example.trua_nay_an_gi.model.OrderDetail;
import com.example.trua_nay_an_gi.repository.IOderDetailRepository;
import com.example.trua_nay_an_gi.service.IOderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OderDetailServiceImpl implements IOderDetailService {
    @Autowired
    IOderDetailRepository oderDetailRepository;
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
