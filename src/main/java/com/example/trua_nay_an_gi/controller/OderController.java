package com.example.trua_nay_an_gi.controller;


import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.product.Coupon;
import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.service.app_users.IAppUserSevice;
import com.example.trua_nay_an_gi.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/public/orders")
public class OderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    private IAppUserSevice userSevice;

    @GetMapping()
   public ResponseEntity<Iterable<Order>> showList(){
        Iterable<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable Long id ) {
        Optional<Order> orders = orderService.findById(id);
        if (!orders.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setId(orders.get().getId());
        order.setAppUser(orders.get().getAppUser());
        return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus(@RequestBody Order order, @PathVariable Long id ) {
        Optional<Order> orders = orderService.findById(id);
        if (!orders.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setId(orders.get().getId());
        order.setAppUser(orders.get().getAppUser());
        return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Order> saveOrder(@RequestBody  Order order) {
        order.setOrderdate(LocalDateTime.now());
        return new ResponseEntity<>(orderService.save(order),HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOder (@PathVariable Long id){
        Optional<Order> orderOptional = orderService.findById(id);
        if (!orderOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.remove(id);
        return new ResponseEntity<>(orderOptional.get(),HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.findById(id);
        if (!orderOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderOptional.get(),HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<Iterable<Order>> findOrderByUser(@PathVariable Long user_id) {
        Optional<AppUser> user = userSevice.findById(user_id);
        Iterable<Order> orderOptional = orderService.findOrdersByAppUser(user.get());
        return new ResponseEntity<>(orderOptional,HttpStatus.OK);
    }


}
