package com.example.trua_nay_an_gi.model.product;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private AppUser user;
    @OneToMany(targetEntity = Product.class)
    private List<Product> products;
    private int quantity;
    private Double totalPrice;

}
