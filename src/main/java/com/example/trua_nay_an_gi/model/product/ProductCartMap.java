//package com.example.trua_nay_an_gi.model.product;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Getter
//@Setter
//@RequiredArgsConstructor
//@Entity(name = "product_cart")
//public class ProductCartMap {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//    @ManyToOne
//    @JoinColumn(name = "cart_id")
//    @JsonBackReference
//    private Cart cart;
//}
