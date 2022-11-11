package com.example.trua_nay_an_gi.model.dto.order;

import com.example.trua_nay_an_gi.constant.Constant;
import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.dto.MerchantDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private LocalDateTime orderTime;
    private Constant.OrderStatus status;
    private String address;
    private Float beforeDiscountAmount;
    private Float discountAmount;
    private Float paidAmount;
    private AppUser appUser;
    private MerchantDto merchant;

    public OrderDto(Long id, LocalDateTime orderTime, Constant.OrderStatus status, AppUser appUser, MerchantDto merchant) {
        this.id = id;
        this.orderTime = orderTime;
        this.status = status;
        this.appUser = appUser;
        this.merchant = merchant;
    }
}
