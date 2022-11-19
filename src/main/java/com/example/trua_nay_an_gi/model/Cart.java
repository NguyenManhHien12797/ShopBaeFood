package com.example.trua_nay_an_gi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private  Double price;
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonBackReference
    private AppUser user;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;


}
