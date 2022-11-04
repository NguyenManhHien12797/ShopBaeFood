package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.app_users.Account;
import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.dto.AccountToken;
import com.example.trua_nay_an_gi.service.account.AccountService;
import com.example.trua_nay_an_gi.service.app_users.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @PostMapping("/register")
//    public ResponseEntity<AppUser> addUser(@RequestBody Optional<AppUser> users){
//        Set<AppRoles> roles= new HashSet<>();
//        AppRoles appRoles= new AppRoles();
//        appRoles.setId(1L);
//        roles.add(appRoles);
//        users.get().setAppRoles(roles);
//        if(users.isPresent()){
//            if(!accountService.existByName(users.get().getUserName())){
//                return new ResponseEntity<>(accountService.save(users.get()), HttpStatus.CREATED);
//            }
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
//    }
}

