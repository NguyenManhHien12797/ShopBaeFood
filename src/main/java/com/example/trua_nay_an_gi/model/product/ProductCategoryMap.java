package com.example.trua_nay_an_gi.model.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "product_category")
public class ProductCategoryMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "category_id")
//    @JsonBackReference
    private Category category;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;


}
