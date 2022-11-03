package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.dto.UserToken;
import com.example.trua_nay_an_gi.service.app_users.AppUserService;
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
    AppUserService appUserService;

    @PostMapping(value = "/login")
    public ResponseEntity<UserToken> login(@RequestBody AppUser appUser) {
        try {
            // Tạo ra 1 đối tượng Authentication.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUser.getUserName(), appUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.createToken(authentication);
            AppUser appUser1 = appUserService.findByUserName(appUser.getUserName());
            return new ResponseEntity(new UserToken(appUser1.getId(), appUser1.getUserName(), token, appUser1.getAppRoles()), HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }

    }
    @PostMapping("/register")
    public ResponseEntity<AppUser> addUser(@RequestBody Optional<AppUser> users){
        Set<AppRoles> roles= new HashSet<>();
        AppRoles appRoles= new AppRoles();
        appRoles.setId(1L);
        roles.add(appRoles);
        users.get().setAppRoles(roles);
        if(users.isPresent()){
            if(!appUserService.existByName(users.get().getUserName())){
                return new ResponseEntity<>(appUserService.save(users.get()), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
}

