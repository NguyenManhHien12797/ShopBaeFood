package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.jwt.JwtUtility;
import com.example.trua_nay_an_gi.model.app_users.Account;
import com.example.trua_nay_an_gi.model.dto.AccountRegisterDTO;
import com.example.trua_nay_an_gi.model.dto.AccountToken;
import com.example.trua_nay_an_gi.payload.request.LoginRequest;
import com.example.trua_nay_an_gi.payload.response.MessageResponse;
import com.example.trua_nay_an_gi.service.account.AccountDetails;
import com.example.trua_nay_an_gi.service.account.AccountService;
import com.example.trua_nay_an_gi.service.account.IAccountService;
import com.example.trua_nay_an_gi.service.account_role.IRoleService;
import com.example.trua_nay_an_gi.service.account_role.RoleService;
import com.example.trua_nay_an_gi.service.app_users.IAppUserSevice;
import com.example.trua_nay_an_gi.service.merchant.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public")
public class LoginSiginController {
//    @Autowired
//    JwtService jwtService;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Tạo ra 1 đối tượng Authentication.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtility.createJwTToken(loginRequest.getUsername());
            AccountDetails userDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            Account account = accountService.findByName(loginRequest.getUsername());
            if(account.getMerchant() != null){
                String status= account.getMerchant().getStatus();
                if("pending".equals(status)){
                    return ResponseEntity.ok(new MessageResponse("Admin chưa phê duyệt đăng ký merchant"));
                }
            }

            return new ResponseEntity(new AccountToken(account.getId(), account.getUserName(), token, roles, account.getMerchant()), HttpStatus.OK);

        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody AccountRegisterDTO request) {
        if (accountService.existsAccountByUserName(request.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username đã được đăng ký"));
        }

        boolean isEnabled = true;
        Account account = new Account(request.getUserName(), request.getPassword(), request.getEmail(), isEnabled);
        accountService.save(account);
        Long idAccountAfterCreated = accountService.findIdUserByUserName(account.getUserName());
        roleService.setDefaultRole(idAccountAfterCreated, 2);
        String avatar = "https://scr.vn/wp-content/uploads/2020/07/Avatar-Facebook-tr%E1%BA%AFng.jpg";

        userSevice.saveUserToRegister(request.getAddress(),avatar,request.getName(),request.getPhone(),idAccountAfterCreated);
        return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công"));
    }

    @PostMapping("/register/merchant")
    public ResponseEntity<?> addMerchant(@RequestBody AccountRegisterDTO request) {
        if (accountService.existsAccountByUserName(request.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Merchant đã được đăng ký"));
        }

        boolean isEnabled = true;
        Account account = new Account(request.getUserName(), request.getPassword(), request.getEmail(), isEnabled);
        accountService.save(account);
        Long idAccountAfterCreated = accountService.findIdUserByUserName(account.getUserName());
        roleService.setDefaultRole(idAccountAfterCreated, 3);
        String avatar = "https://scr.vn/wp-content/uploads/2020/07/Avatar-Facebook-tr%E1%BA%AFng.jpg";
        String status = "pending";
        merchantService.saveMerchantToRegister(request.getAddress(),avatar,request.getName(),request.getPhone(),status,idAccountAfterCreated);
        return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công"));
    }
}

