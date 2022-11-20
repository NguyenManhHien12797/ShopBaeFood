package com.example.trua_nay_an_gi.controller;


import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.model.Order;
import com.example.trua_nay_an_gi.service.IAppUserSevice;
import com.example.trua_nay_an_gi.service.IOrderService;
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

    @GetMapping()
   public ResponseEntity<Iterable<Order>> getAllOrder(){
        Iterable<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable Long id ) {
        return new ResponseEntity<>(orderService.updateOrder(id,order), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus(@RequestBody Order order, @PathVariable Long id ) {
        return new ResponseEntity<>(orderService.updateOrder(id, order), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Order> saveOrder(@RequestBody  Order order) {
        return new ResponseEntity<>(orderService.save(order),HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOder (@PathVariable Long id){
        orderService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.findOrderById(id),HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<Iterable<Order>> findOrderByUser(@PathVariable Long user_id) {
        return new ResponseEntity<>(orderService.findOrdersByAppUser(user_id),HttpStatus.OK);
    }


}
