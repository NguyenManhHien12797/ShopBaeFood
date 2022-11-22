package com.example.trua_nay_an_gi.model.dto;

import com.example.trua_nay_an_gi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDto {
    private Product product;
    private int quantity;
}
