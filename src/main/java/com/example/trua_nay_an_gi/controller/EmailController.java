package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.app_users.Account;
import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.product.Mail;
import com.example.trua_nay_an_gi.service.account.IAccountService;
import com.example.trua_nay_an_gi.service.app_users.AppUserService;
import com.example.trua_nay_an_gi.service.app_users.IAppUserSevice;
import com.example.trua_nay_an_gi.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/public/mail")
public class EmailController {
    @Autowired
    private IAppUserSevice userService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private MailService mailService;
    @Autowired
    PasswordEncoder encoder;
    // http://localhost:8080/mail/sender
    @PostMapping("/sender")
    public ResponseEntity<Mail> fogotPass(
//            @RequestBody Optional<AppUser> appUser
    ){
//        AppUser appUsers=userService.findByMail(appUser.get().getEmail());
        Mail mail = new Mail();
        mail.setMailFrom("nguyenhuuquyet07092001@gmail.com");
        mail.setMailTo("nguyenhien81f@gmail.com");
        mail.setMailSubject("subject");
        mail.setMailContent("test");

        mailService.sendEmail(mail);
        return new ResponseEntity<>(mail, HttpStatus.OK);
    }


    @PostMapping("/accept")
    public ResponseEntity<Mail> acceptRegistration(@RequestParam String email, @RequestParam String name){
        Mail mail = new Mail();
        mail.setMailFrom("nguyenhuuquyet07092001@gmail.com");
        mail.setMailTo(email);
        mail.setMailSubject("Mail xác nhận đăng ký");
        mail.setMailContent("Dear "+name+"! Cảm ơn bạn đã đăng ký làm thành viên của hệ thống");

        mailService.sendEmail(mail);
        return new ResponseEntity<>(mail, HttpStatus.OK);
    }

    @PostMapping("/refuse")
    public ResponseEntity<Mail> refuseToRegister(@RequestParam String email){
        Mail mail = new Mail();
        mail.setMailFrom("nguyenhuuquyet07092001@gmail.com");
        mail.setMailTo(email);
        mail.setMailSubject("Mail từ chối đăng ký làm người bán");
        mail.setMailContent("Cảm ơn bạn đã đăng ký làm merchant của hệ thống. Chúng tôi rất tiếc khi bạn chưa đủ điều kiện để đăng ký làm người bán.");

        mailService.sendEmail(mail);
        return new ResponseEntity<>(mail, HttpStatus.OK);
    }
//    http://localhost:8080/api/public/mail/chkncotp
    @PostMapping("/chkncotp")
    public ResponseEntity<?> checkNameCreateOTP(@RequestParam String name){
        Optional<Account> account= Optional.ofNullable(accountService.findByName(name));
        if(!account.isPresent()){
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
        double randomDouble = Math.random();
        randomDouble = randomDouble * 1000000+1 ;
        int OTP= (int) randomDouble;
        account.get().setOtp(String.valueOf(OTP));
        accountService.save(account.get());
        Mail mail= new Mail();
        mail.setMailTo(account.get().getEmail());
        mail.setMailFrom("nguyenhuuquyet07092001@gmail.com");
        mail.setMailSubject("Mã xác nhận OTP");
        mail.setMailContent("Mã OTP của bạn là:"+OTP+"\nVui lòng không chia sẻ với ai\nMời nhấp link bên dưới để đến trang xác nhận OTP\nhttp://localhost:4200/forgotpass/otp");
        mailService.sendEmail(mail);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
//    http://localhost:8080/api/public/mail/confirmOtp
    @PostMapping("/confirmOtp")
    public ResponseEntity<?> confirmOtp(@RequestParam String otp, String name, String pass){
        Optional<Account> account= Optional.ofNullable(accountService.findByName(name));
        if(!account.isPresent()){
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
        if(otp.equals(account.get().getOtp())){
            account.get().setPassword(encoder.encode(pass));
            account.get().setOtp(null);
            accountService.save(account.get());
            return new ResponseEntity<>(true,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("sai otp",HttpStatus.OK);
        }
    }

}
