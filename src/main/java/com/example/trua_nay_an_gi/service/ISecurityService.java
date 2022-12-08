package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.dto.AccountRegisterDTO;
import com.example.trua_nay_an_gi.model.dto.AccountToken;
import com.example.trua_nay_an_gi.payload.request.LoginRequest;

public interface ISecurityService {
    String addUser(AccountRegisterDTO accountRegisterDTO);
    String addMerchant(AccountRegisterDTO accountRegisterDTO);
}
