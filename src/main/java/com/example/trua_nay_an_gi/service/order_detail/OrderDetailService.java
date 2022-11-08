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
    IOrderDetailRepository orderDetailRepository;

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    IOrderService orderService;

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
    public Iterable<OrderDetail> findAllByOrder(Order order) {
        return orderDetailRepository.findAllByOrder(order);
    }

    @Override
    public Iterable<OrderDetail> findAllByProductId(Long id) {
        return orderDetailRepository.findAllByProductId(id);
    }

    @Override
    public Iterable<Order> findAllOrderByProductId(Long id) {
        List<OrderDetail> orderDetails = (List<OrderDetail>) findAllByProductId(id);
        List<Long> ordersIds = new ArrayList<>();
        for (OrderDetail orderDetail: orderDetails) {
            Long orderId = orderDetail.getOrder().getId();
            if (!ordersIds.contains(orderId)){
                ordersIds.add(orderId);
            }
        }
        List<Order> orders = new ArrayList<>();
        for (Long orderId : ordersIds) {
            Optional<Order> findOrder = orderService.findById(orderId);
            if (findOrder.isPresent()){
                orders.add(findOrder.get());
            }
        }

        return orders;
    }
}
