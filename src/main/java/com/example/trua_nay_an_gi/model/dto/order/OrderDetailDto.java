package com.example.trua_nay_an_gi.model.dto.order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class OrderDetailDto {
    private Long id;
    private Integer quantity;
    private Long price;
//    private CouponDTO coupon;
//    private FoodDTO food;

    private OrderDto order;

    public OrderDetailDto(Long id, Integer quantity, Long price, OrderDto order) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }
//    public OrderDetailDTO(Long id, Integer quantity, Long price,OrderDto order) {
//        this.id = id;
//        this.quantity = quantity;
//        this.price = price;
////        this.coupon = coupon;
////        this.food = food;
//        this.order = order;
    }
