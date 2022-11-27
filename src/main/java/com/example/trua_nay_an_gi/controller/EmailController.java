package com.example.trua_nay_an_gi.controller;


import com.example.trua_nay_an_gi.model.Mail;
import com.example.trua_nay_an_gi.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/public/mail")
public class EmailController {
    @Autowired
    private IMailService IMailService;

    @PostMapping("/sender")
    public ResponseEntity<?> fogotPass() {
        IMailService.fogotPass();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/accept")
    public ResponseEntity<Mail> acceptRegistration(@RequestParam String email, @RequestParam String name) {
        IMailService.acceptRegistration(email, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/refuse")
    public ResponseEntity<Mail> refuseToRegister(@RequestParam String email) {
        IMailService.refuseToRegister(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/chkncotp")
    public ResponseEntity<?> checkNameCreateOTP(@RequestParam String name) {
        return new ResponseEntity<>(IMailService.checkNameCreateOTP(name), HttpStatus.OK);
    }

    @PostMapping("/confirmOtp")
    public ResponseEntity<?> confirmOtp(@RequestParam String otp, String name, String pass) {
        return new ResponseEntity<>(IMailService.confirmOtp(otp, name, pass), HttpStatus.OK);
    }

}
