package com.example.trua_nay_an_gi.controller;


import com.example.trua_nay_an_gi.model.product.Coupon;
import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
public class OderController {
    @Autowired
    IOrderService orderService;

    @GetMapping
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
        return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Order> saveOrder(@RequestBody  Order order) {
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
}
