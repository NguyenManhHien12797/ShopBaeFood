package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.jwt.JwtUtility;
import com.example.trua_nay_an_gi.model.Account;
import com.example.trua_nay_an_gi.model.dto.AccountRegisterDTO;
import com.example.trua_nay_an_gi.model.dto.AccountToken;
import com.example.trua_nay_an_gi.payload.request.LoginRequest;
import com.example.trua_nay_an_gi.payload.response.MessageResponse;
import com.example.trua_nay_an_gi.service.*;
import com.example.trua_nay_an_gi.service.seviceImpl.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public")
public class SecurityController {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ISecurityService securityService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            // Tạo ra 1 đối tượng Authentication.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtility.createJwTToken(loginRequest.getUserName());
            AccountDetails userDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            Account account = accountService.findByName(loginRequest.getUserName());
            if(account.getMerchant() != null){
                String status= account.getMerchant().getStatus();
                if("pending".equals(status)){
                    return ResponseEntity.ok(new MessageResponse("Admin chưa phê duyệt đăng ký merchant"));
                }
                if("block".equals(status)){
                    return ResponseEntity.ok(new MessageResponse("Tài khoản của bạn đang bị khóa"));
                }
                if("refuse".equals(status)){
                    return ResponseEntity.ok(new MessageResponse("Admin đã từ chối đăng ký merchant"));
                }
            }if(account.getUser() != null){
                String status= account.getUser().getStatus();
                if("block".equals(status)){
                    return ResponseEntity.ok(new MessageResponse("Tài khoản của bạn đang bị khóa"));
                }
            }

            return new ResponseEntity(new AccountToken(account.getId(), account.getUserName(), token, roles, account.getMerchant(),account.getUser()), HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.ok(new MessageResponse("Sai roi"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody AccountRegisterDTO request) {
        return ResponseEntity.ok(securityService.addUser(request));
            }

    @PostMapping("/register/merchant")
    public ResponseEntity<?> addMerchant(@RequestBody AccountRegisterDTO request) {
        return ResponseEntity.ok(securityService.addMerchant(request));
    }
}

