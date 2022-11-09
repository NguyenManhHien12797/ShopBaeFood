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
    IOrderRepository orderRepository;

    @Autowired
    IOrderDetailService orderDetailService;


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
    public OrderDto getOrderDto(Long orderId) {
        Optional<Order> findOrder = findById(orderId);
        if (!findOrder.isPresent()) {
            return null;
        }

        Order order = findOrder.get();
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderId);
        orderDto.setDelivery(order.getDelivery());
        orderDto.setStatus(order.getStatus());

        CartDto cartDto = new CartDto();
        Iterable<OrderDetail> orderDetails = orderDetailService.findAllByOrder(order);
        List<OrderDetail> orderDetailList =
                StreamSupport.stream(orderDetails.spliterator(), false)
                        .collect(Collectors.toList());
        for (OrderDetail orderDetail : orderDetailList) {
            CartDetailDto cartDetailDto = new CartDetailDto(orderDetail.getProduct(), orderDetail.getQuantity());
            cartDto.addCartDetailDto(cartDetailDto);
        }
        orderDto.setCart(cartDto);

        Merchant merchant = orderDetailList.get(0).getProduct().getMerchant();
        orderDto.setMerchant(merchant);
        orderDto.setCreateDate(order.getCreateDate());
        return orderDto;
    }

    @Override
    public List<OrderDto> findAllOrderDtoByUserId(Long userId) {
        Iterable<Order> orders =orderRepository.findAllByAppUser_IdOrderByCreateDateDesc(userId);
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order order : orders){
            OrderDto orderDto = getOrderDto(order.getId());
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @Override
    public Iterable<Order> findAllByUserId(Long id)  {
        return orderRepository.findAllByAppUser_IdOrderByCreateDateDesc(id);
    }

    @Override
    public Iterable<OrderDtoByOwner> findAllOrderDtoByOwnerId(Long ownerId) {
        return orderRepository.findOrderByOwnerIdOrderByCreateDateDesc(ownerId);
    }
}
