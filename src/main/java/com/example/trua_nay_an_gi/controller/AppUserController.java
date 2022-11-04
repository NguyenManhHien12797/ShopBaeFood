package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.service.app_users.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class AppUserController {
    @Autowired
    AppUserService appUserService;

    @GetMapping("/api/users")
    public ResponseEntity<Iterable<AppUser>> showList() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<AppUser> customerList = (List<AppUser>) appUserService.findAll();
        if (customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user, @PathVariable Long id){
        Optional<AppUser> appUser= appUserService.findById(id);
        if(!appUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(appUser.get().getId());
        return new ResponseEntity<>(appUserService.save(user),HttpStatus.OK);
    }
    @PutMapping("/active/{id}")
    public ResponseEntity<AppUser> active(@RequestBody AppUser user, @PathVariable Long id){
        Optional<AppUser> appUser= appUserService.findById(id);
        if(!appUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(appUser.get().getId());
        return new ResponseEntity<>(appUserService.save(user),HttpStatus.OK);
    }

}
