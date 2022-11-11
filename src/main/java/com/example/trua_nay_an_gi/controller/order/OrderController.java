package com.example.trua_nay_an_gi.controller.order;

import com.example.trua_nay_an_gi.model.dto.order.OrderDto;
import com.example.trua_nay_an_gi.model.product.ErrorMessage;
import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.service.app_users.IAppUserSevice;
import com.example.trua_nay_an_gi.service.order.IOrderService;
import com.example.trua_nay_an_gi.service.order_detail.IOrderDetailService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.collections4.IterableUtils;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<ResponseBody> findAll(){
        List<Order> orders = IterableUtils.toList(orderService.findAll());
        if (orders.isEmpty()) {
            return new ResponseEntity<>(new ResponseBody(Response.DATA_NOT_FOUND, null), HttpStatus.NOT_FOUND);
        }
        List<OrderDTO> orderDTOS = OrderMapper.INSTANCE.mapOrderListToDTO(orders);
        return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, orderDTOS), HttpStatus.OK);
    }


//    @Autowired
//    IAppUserSevice userSevice;
//
//    @Autowired
//    IOrderService orderService;
//
//    @Autowired
//    IOrderDetailService orderDetailService;
//
//
//    /*
//        Trả về đối tượng OrderDto
//     */
//    @GetMapping("/{orderId}")
//    public ResponseEntity<?> getOrderDto(@PathVariable Long orderId) {
//        OrderDto orderDto = orderService.getOrderDto(orderId);
//        return new ResponseEntity<>(orderDto, HttpStatus.OK);
//    }
//
//
//    @GetMapping("/products/{id}")
//    public ResponseEntity<?> getAllOrderByProduct(@PathVariable Long id) {
//        List<Order> orders = (List<Order>) orderDetailService.findAllOrderByProductId(id);
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }
//
//    @GetMapping("/users/{id}")
//    public ResponseEntity<?> getAllOrderByUserId(@PathVariable Long id) {
//        Iterable<Order> orders = orderService.findAllByUserId(id);
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }
//
//    @PostMapping("/{orderId}/cancels")
//    public ResponseEntity<?> userCancelOrderById(@PathVariable Long orderId) {
//        Optional<Order> findOrderById = orderService.findById(orderId);
//        if (!findOrderById.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        Order order = findOrderById.get();
//        if (order.getStatus() != 0) {
//            ErrorMessage errorMessage = new ErrorMessage("Không thể thực hiện thao tác");
//            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//        }
//
//        order.setStatus(-1);
//        orderService.save(order);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
//    @GetMapping("/{orderId}/accept")
//    public ResponseEntity<?> merchantAcceptOrder(@PathVariable Long orderId) {
//        Optional<Order> findOrder = orderService.findById(orderId);
//        if (!findOrder.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        Order order = findOrder.get();
//        if (order.getStatus() != 0) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        order.setStatus(1);
//        order = orderService.save(order);
//        return new ResponseEntity<>(order, HttpStatus.OK);
//    }
}
