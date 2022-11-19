package com.example.trua_nay_an_gi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortDescription;
    private String numberOrder;
    private Double oldPrice;
    private Double newPrice;
    private String image;
    private int quantity;

    @Column(name = "deleteFlag", columnDefinition = "boolean default true")
    private boolean deleteFlag;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
//    @JsonBackReference
    private Merchant merchant;


}
