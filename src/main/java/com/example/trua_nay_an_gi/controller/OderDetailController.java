package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.Order;
import com.example.trua_nay_an_gi.model.OrderDetail;
import com.example.trua_nay_an_gi.service.IOderDetailService;
import com.example.trua_nay_an_gi.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/api/public/orderdetails")
@RestController
public class OderDetailController {
    @Autowired
    IOderDetailService oderDetailService;
    @Autowired
    private IOrderService orderService;


    @GetMapping
    public ResponseEntity<Iterable<OrderDetail>> ShowOrdersDetail() {
        Iterable<OrderDetail> orderDetails = oderDetailService.findAll();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> findById(@PathVariable Long id) {
        return new ResponseEntity<>(oderDetailService.findOrderDetailById(id),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OrderDetail> saveOrderdetail(@RequestBody  OrderDetail orderDetail) {
        return new ResponseEntity<>(oderDetailService.save(orderDetail),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderdetail (@RequestBody OrderDetail orderDetail, @PathVariable Long id ) {
        return new ResponseEntity<>(oderDetailService.updateOrderdetal(id,orderDetail),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDetail> deleteOderdetail (@PathVariable Long id){
        oderDetailService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Iterable<OrderDetail>> findOrderDetailByOrderId(@PathVariable Long id) {
        Iterable<OrderDetail> orderDetailOptional = oderDetailService.findOrderDetailsByOrderId(id);
        return new ResponseEntity<>(orderDetailOptional,HttpStatus.OK);
    }
 }
