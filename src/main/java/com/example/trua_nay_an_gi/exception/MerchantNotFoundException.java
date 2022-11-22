package com.example.trua_nay_an_gi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MerchantNotFoundException extends  RuntimeException{
    private int code;
    private String message;
}
