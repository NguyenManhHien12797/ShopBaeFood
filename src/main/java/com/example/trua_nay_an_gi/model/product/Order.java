package com.example.trua_nay_an_gi.model.product;

import com.example.trua_nay_an_gi.constant.Constant;
import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.delivery.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    private AppUser appUser;

    private Date createDate;

//    private Constant.OrderStatus status;

    @ManyToOne
    private Coupon coupon;

    @OneToOne
    private Delivery delivery;

    private double serviceFee;
    private double shippingFee;
    private double discountAmount;
    private double totalFee;

    @Column(columnDefinition = "VARCHAR(500)")
    private String merchantNote;

    @Column(columnDefinition = "VARCHAR(500)")
    private String shippingNote;

    @Column(columnDefinition = "TINYINT default 0")
    private int status;
}
