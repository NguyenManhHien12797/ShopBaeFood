package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.product.Coupon;
import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.model.product.OrderDetail;
import com.example.trua_nay_an_gi.service.oder_detail.OderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/api/oderdetails")
@RestController
public class OderDetailController {
    @Autowired
    OderDetailService oderDetailService;

    @GetMapping
    public ResponseEntity<Iterable<OrderDetail>> ShowOrdersDetail() {
        Iterable<OrderDetail> orderDetails = oderDetailService.findAll();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> findById(@PathVariable Long id) {
        Optional<OrderDetail> orderDetailOptional = oderDetailService.findById(id);
        if (!orderDetailOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDetailOptional.get(),HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderDetail> saveOrderdetail(@RequestBody  OrderDetail orderDetail) {
        return new ResponseEntity<>(oderDetailService.save(orderDetail),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderdetail (@RequestBody OrderDetail orderDetail, @PathVariable Long id ) {
        Optional<OrderDetail> orderDetails = oderDetailService.findById(id);
        if (!orderDetails.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetail.setId(orderDetails.get().getId());
        return new ResponseEntity<>(oderDetailService.save(orderDetail),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDetail> deleteOderdetail (@PathVariable Long id){
        Optional<OrderDetail> orderDetailOptional = oderDetailService.findById(id);
        if (!orderDetailOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oderDetailService.remove(id);
        return new ResponseEntity<>(orderDetailOptional.get(),HttpStatus.NO_CONTENT);
    }
 }
