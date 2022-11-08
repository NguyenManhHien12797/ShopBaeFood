package com.example.trua_nay_an_gi.service.order;

import com.example.trua_nay_an_gi.model.dto.order.OrderDto;
import com.example.trua_nay_an_gi.model.dto.order.OrderDtoByOwner;
import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.service.GeneralService;

import java.util.List;

public interface IOrderService extends GeneralService<Order> {

    OrderDto getOrderDto(Long orderId);

    List<OrderDto> findAllOrderDtoByUserId(Long userId);

    Iterable<Order> findAllByUserId(Long id);

    Iterable<OrderDtoByOwner> findAllOrderDtoByOwnerId(Long ownerId);
}
