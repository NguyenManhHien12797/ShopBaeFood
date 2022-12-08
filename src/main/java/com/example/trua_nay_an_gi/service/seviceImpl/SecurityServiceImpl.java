package com.example.trua_nay_an_gi.service.seviceImpl;


import com.example.trua_nay_an_gi.jwt.JwtUtility;
import com.example.trua_nay_an_gi.model.Account;
import com.example.trua_nay_an_gi.model.dto.AccountRegisterDTO;
import com.example.trua_nay_an_gi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SecurityServiceImpl implements ISecurityService {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IAppUserSevice userSevice;

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;



    @Override
    public String addUser(AccountRegisterDTO accountRegisterDTO) {
        if (accountService.existsAccountByUserName(accountRegisterDTO.getUserName())) {
            return "Username đã được đăng ký";
        }

        String status = "active";
        boolean isEnabled = true;
        Account account = new Account(accountRegisterDTO.getUserName(), encoder.encode(accountRegisterDTO.getPassword()), accountRegisterDTO.getEmail(), isEnabled);
        accountService.save(account);
        Long idAccountAfterCreated = accountService.findIdUserByUserName(account.getUserName());
        roleService.setDefaultRole(idAccountAfterCreated, 2);
        String avatar = "https://scr.vn/wp-content/uploads/2020/07/Avatar-Facebook-tr%E1%BA%AFng.jpg";
        userSevice.saveUserToRegister(accountRegisterDTO.getAddress(), avatar, accountRegisterDTO.getName(), accountRegisterDTO.getPhone(), idAccountAfterCreated, status);
        return "Đăng ký tài khoản thành công";
    }

    @Override
    public String addMerchant(AccountRegisterDTO accountRegisterDTO) {
        if (accountService.existsAccountByUserName(accountRegisterDTO.getUserName())) {
            return "Merchant đã được đăng ký";
        }

        boolean isEnabled = true;
        Account account = new Account(accountRegisterDTO.getUserName(), encoder.encode(accountRegisterDTO.getPassword()), accountRegisterDTO.getEmail(), isEnabled);
        accountService.save(account);
        Long idAccountAfterCreated = accountService.findIdUserByUserName(account.getUserName());
        roleService.setDefaultRole(idAccountAfterCreated, 3);
        String avatar = "https://scr.vn/wp-content/uploads/2020/07/Avatar-Facebook-tr%E1%BA%AFng.jpg";
        String status = "pending";
        merchantService.saveMerchantToRegister(accountRegisterDTO.getAddress(), avatar, accountRegisterDTO.getName(), accountRegisterDTO.getPhone(), status, idAccountAfterCreated);
        return "Đăng ký tài khoản thành công! Vui lòng đợi Admin xác nhận";
    }
}
