package com.example.trua_nay_an_gi.model.dto.order;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.model.delivery.Delivery;
import com.example.trua_nay_an_gi.model.dto.cart.CartDto;

import java.util.Date;

public class OrderDto {
    private Long id;
    private CartDto cart;
    private Delivery delivery;
    private Merchant merchant;
    private Date createDate;
    private int status;
}
