package com.example.trua_nay_an_gi.model.product;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.delivery.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @JoinColumn(name = "AppUser_id")
    private AppUser appUser;

    private String note;

    private LocalDateTime orderdate;

    private String status;
    private Long merchant_id;
    private double totalPrice;

}
