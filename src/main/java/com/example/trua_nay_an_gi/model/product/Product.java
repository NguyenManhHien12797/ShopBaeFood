package com.example.trua_nay_an_gi.model.product;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @Column(name = "deleteFlag", columnDefinition = "boolean default true")
    private boolean deleteFlag;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    @JsonBackReference
    private Merchant merchant;



}
