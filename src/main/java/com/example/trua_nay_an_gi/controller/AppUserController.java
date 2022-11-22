package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.service.IAppUserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class AppUserController {
    @Autowired
    IAppUserSevice appUserService;

    @GetMapping()
    public ResponseEntity<Iterable<AppUser>> getAllAppUser() {
        return new ResponseEntity<>(appUserService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(appUserService.findUserById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user, @PathVariable Long id){
        return new ResponseEntity<>(appUserService.updateUser(id,user),HttpStatus.OK);
    }

}
