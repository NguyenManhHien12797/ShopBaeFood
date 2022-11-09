package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.jwt.JwtUtility;
import com.example.trua_nay_an_gi.model.app_users.Account;
import com.example.trua_nay_an_gi.model.app_users.AccountRoleMap;
import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.dto.AccountRegisterDTO;
import com.example.trua_nay_an_gi.model.dto.AccountToken;
import com.example.trua_nay_an_gi.payload.request.LoginRequest;
import com.example.trua_nay_an_gi.service.account.AccountDetails;
import com.example.trua_nay_an_gi.service.account.AccountService;
import com.example.trua_nay_an_gi.service.account_role.AccountRoleService;
import org.springframework.beans.BeanUtils;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private AccountService accountService;

    @Autowired
    AccountRoleService accountRoleService;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping(value = "/login")
    public ResponseEntity<AccountToken> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Tạo ra 1 đối tượng Authentication.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtility.createJwTToken(loginRequest.getUsername());
            AccountDetails userDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<String>roles= userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            Account account = accountService.findByName(loginRequest.getUsername());


            return new ResponseEntity(new AccountToken(account.getId(), account.getUserName(), token,roles, account.getMerchant()), HttpStatus.OK);

        } catch (Exception e) {
            return null;
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Account> addUser(@RequestBody AccountRegisterDTO request){

          Account account = new Account(request.getUserName(), request.getPassword());




        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
}

