package com.example.trua_nay_an_gi.model.product;

//import com.example.trua_nay_an_gi.model.app_users.MerchantCategoryMap;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private Set<ProductCategoryMap> productCategoryMapSet;
}
