package com.example.trua_nay_an_gi.controller;

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
 }
