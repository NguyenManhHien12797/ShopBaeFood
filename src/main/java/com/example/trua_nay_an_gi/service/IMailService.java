package com.example.trua_nay_an_gi.service;

import com.example.trua_nay_an_gi.model.Mail;

public interface IMailService {
    void sendEmail(Mail mail);

    void fogotPass();
    void acceptRegistration(String email, String name);
    void refuseToRegister(String email);

    boolean checkNameCreateOTP(String name);

    boolean confirmOtp(String otp, String name, String pass);

}
