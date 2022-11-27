package com.example.trua_nay_an_gi.service.seviceImpl;


import com.example.trua_nay_an_gi.exception.OrderDetailNotFoundException;
import com.example.trua_nay_an_gi.model.Order;
import com.example.trua_nay_an_gi.model.OrderDetail;
import com.example.trua_nay_an_gi.repository.IOderDetailRepository;
import com.example.trua_nay_an_gi.service.IOderDetailService;
import com.example.trua_nay_an_gi.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OderDetailServiceImpl implements IOderDetailService {
    @Autowired
    IOderDetailRepository oderDetailRepository;

    @Autowired
    IOrderService orderService;
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
    public Iterable<OrderDetail> findOrderDetailsByOrderId(Long id) {
        Order order = orderService.findOrderById(id);
        return oderDetailRepository.findOrderDetailsByOrder(order);
    }

    @Override
    public OrderDetail findOrderDetailById(Long id) {
        return  oderDetailRepository.findOrderDetailById(id).orElseThrow(() -> new OrderDetailNotFoundException(404, "OrderDetail by id "+ id + "was not found"));
    }

    @Override
    public OrderDetail updateOrderdetal(Long id, OrderDetail orderDetail) {
        OrderDetail orderDetail1 = this.findOrderDetailById(id);
        orderDetail.setId(orderDetail1.getId());
        return oderDetailRepository.save(orderDetail);
    }


}
