package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.product.Mail;
import com.example.trua_nay_an_gi.service.app_users.AppUserService;
import com.example.trua_nay_an_gi.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/mail")
public class EmailController {
    @Autowired
    private AppUserService userService;
    @Autowired
    private MailService mailService;
    // http://localhost:8080/mail/sender
    @PostMapping("/sender")
    public ResponseEntity<Mail> fogotPass(
//            @RequestBody Optional<AppUser> appUser
    ){
//        AppUser appUsers=userService.findByMail(appUser.get().getEmail());
        Mail mail = new Mail();
        mail.setMailFrom("nguyenhuuquyet07092001@gmail.com");
        mail.setMailTo("quuyyeett@gmail.com");
        mail.setMailSubject("subject");
        mail.setMailContent("test");

        mailService.sendEmail(mail);
        return new ResponseEntity<>(mail, HttpStatus.OK);
    }
}
