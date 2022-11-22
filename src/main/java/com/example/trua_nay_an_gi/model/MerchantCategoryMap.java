package com.example.trua_nay_an_gi.model;

import com.example.trua_nay_an_gi.model.Category;
import com.example.trua_nay_an_gi.model.Merchant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "merchant_category")
public class MerchantCategoryMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    @JsonBackReference
    private Merchant merchant;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
