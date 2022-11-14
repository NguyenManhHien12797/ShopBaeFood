package com.example.trua_nay_an_gi.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class CartDTO {
    private Long id;
    private int quantity;
    private  double price;
    private Long user_id;
    private Long product_id;
    private Double totalPrice;


}
