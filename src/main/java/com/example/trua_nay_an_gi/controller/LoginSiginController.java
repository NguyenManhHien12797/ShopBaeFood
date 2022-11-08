package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.app_users.Account;
import com.example.trua_nay_an_gi.model.app_users.AccountRoleMap;
import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.dto.AccountRegisterDTO;
import com.example.trua_nay_an_gi.model.dto.AccountToken;
import com.example.trua_nay_an_gi.service.account.AccountService;
import com.example.trua_nay_an_gi.service.account_role.AccountRoleService;
import com.example.trua_nay_an_gi.service.app_users.JwtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class LoginSiginController {
    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRoleService accountRoleService;

    @PostMapping(value = "/login")
    public ResponseEntity<AccountToken> login(@RequestBody Account account) {
        try {
            // Tạo ra 1 đối tượng Authentication.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getUserName(), account.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.createToken(authentication);
            Account account1 = accountService.findByName(account.getUserName());
            //lỗi
            return new ResponseEntity(new AccountToken(account1.getId(), account1.getUserName(), token,accountService.findAppRoleByAccountId(account1.getId())), HttpStatus.OK);

        } catch (Exception e) {
            return null;
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Account> addUser(@RequestBody AccountRegisterDTO request){

        Set<AccountRoleMap> accountRoleMapSet= new HashSet<>();
        AccountRoleMap accountRoleMap= new AccountRoleMap();
        // đoạn này e đang tạo user nên có thể set role mặc định của nó là USER  luôn
//        AppRoles appRoles= new AppRoles();
//        appRoles.setId(2L);
//        accountRoleMap.setRole(appRoles);
        //Copy thuôc tính của dto cho account, cái này nó sẽ copy theo tên field
        Account account = new Account();
        BeanUtils.copyProperties(request, account);
        //Copy đto cho user
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(request,appUser);
        accountRoleMap.setAccount(account);
        account = accountService.save(account);
        //Lấy ra đối tượng appROle có name = USER;
        AppRoles appRoles= new AppRoles();
        accountRoleMap.setAccount(account);

        //Cái này e gọi vào repo để lấy ra thằng role tương ứng ở bảng appRole xong set vào
        //
//        accountRoleMap.setRole(account);
        //gọi service để lưu accountRoleMap
//        Account account= new Account(request.get().getUserName(),
//                request.get().getPassword(),true,
//                request.get().getEmail(),
//                accountRoleMapSet);
//        accountRoleMapSet.add(accountRoleMap);

//        if(request.isPresent()){
//                return new ResponseEntity<>(accountService.save(account), HttpStatus.CREATED);
//        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
}

