package com.example.trua_nay_an_gi.service.seviceImpl;


import com.example.trua_nay_an_gi.model.Account;
import com.example.trua_nay_an_gi.model.Mail;
import com.example.trua_nay_an_gi.service.IAccountService;
import com.example.trua_nay_an_gi.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service("mailService")
public class MailServiceImpl implements IMailService {

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    IAccountService accountService;
    @Autowired
    PasswordEncoder encoder;

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    public void sendEmail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "ShopBeaFood nhóm 3"));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());

            mailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            logger.error(String.valueOf(e));
        } catch (UnsupportedEncodingException e) {
            logger.error(String.valueOf(e));
        }
    }

    @Override
    public void fogotPass() {
        Mail mail = new Mail();
        mail.setMailFrom("nguyenhuuquyet07092001@gmail.com");
        mail.setMailTo("nguyenhien81f@gmail.com");
        mail.setMailSubject("subject");
        mail.setMailContent("test");
        this.sendEmail(mail);
    }

    @Override
    public void acceptRegistration(String email, String name) {
        Mail mail = new Mail();
        mail.setMailFrom("nguyenhuuquyet07092001@gmail.com");
        mail.setMailTo(email);
        mail.setMailSubject("Mail xác nhận đăng ký");
        mail.setMailContent("Dear "+name+"! Cảm ơn bạn đã đăng ký làm thành viên của hệ thống");
        this.sendEmail(mail);
    }

    @Override
    public void refuseToRegister(String email) {
        Mail mail = new Mail();
        mail.setMailFrom("nguyenhuuquyet07092001@gmail.com");
        mail.setMailTo(email);
        mail.setMailSubject("Mail từ chối đăng ký làm người bán");
        mail.setMailContent("Cảm ơn bạn đã đăng ký làm merchant của hệ thống. Chúng tôi rất tiếc khi bạn chưa đủ điều kiện để đăng ký làm người bán.");
        this.sendEmail(mail);
    }

    @Override
    public boolean checkNameCreateOTP(String name) {
        Account account= accountService.findByName(name);
        double randomDouble = Math.random();
        randomDouble = randomDouble * 1000000+1 ;
        int OTP= (int) randomDouble;
        account.setOtp(String.valueOf(OTP));
        accountService.save(account);
        Mail mail= new Mail();
        mail.setMailTo(account.getEmail());
        mail.setMailFrom("nguyenhuuquyet07092001@gmail.com");
        mail.setMailSubject("Mã xác nhận OTP");
        mail.setMailContent("Mã OTP của bạn là:"+OTP+"\nVui lòng không chia sẻ với ai\nMời nhấp link bên dưới để đến trang xác nhận OTP\nhttp://localhost:4200/forgotpass/otp");
        this.sendEmail(mail);
        return true;
    }

    @Override
    public boolean confirmOtp(String otp, String name, String pass) {
        Account account = accountService.findByName(name);
        boolean check = false;
        if (otp.equals(account.getOtp())) {
            account.setPassword(encoder.encode(pass));
            account.setOtp(null);
            accountService.save(account);
            check = true;
            return check;
        } else {
            return check;
        }
    }
}
