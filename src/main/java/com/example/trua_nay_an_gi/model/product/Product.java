package com.example.trua_nay_an_gi.model.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortDecription;
    private String numberOrder;
    private Double oldPrice;
    private Double newPrice;
    private String image;
    @ManyToMany
    private List<Category> categories;
    @ManyToOne
    private Cart cart;

}
