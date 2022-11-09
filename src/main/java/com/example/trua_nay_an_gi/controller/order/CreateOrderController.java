package com.example.trua_nay_an_gi.controller.order;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.dto.order.OrderDto;
import com.example.trua_nay_an_gi.model.product.ErrorMessage;
import com.example.trua_nay_an_gi.model.product.Order;
import com.example.trua_nay_an_gi.service.app_users.IAppUserSevice;
import com.example.trua_nay_an_gi.service.cart.CartService;
import com.example.trua_nay_an_gi.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/current-user")
public class CreateOrderController {
    @Autowired
    IAppUserSevice appUserSevice;

    @Autowired
    IOrderService orderService;

    @Autowired
    CartService cartService;

    // Input 1 orderDto => tạo vào lưu order vào DB
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        AppUser currentUser = appUserSevice.findByUsername(principal.getName()).get();

        if (currentUser == null) {
            ErrorMessage errorMessage = new ErrorMessage("Người dùng chưa đăng nhập");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        if (orderDto.getCart().getCartDetails().size() == 0) {
            ErrorMessage errorMessage = new ErrorMessage("Giỏ hàng trống");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        Order order = cartService.saveOrderToDB(currentUser, orderDto);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
