package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.app_users.Account;
import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.model.dto.AccountRegisterDTO;
import com.example.trua_nay_an_gi.model.dto.MerchantDTO;
import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.service.account.AccountService;
import com.example.trua_nay_an_gi.service.account.IAccountService;
import com.example.trua_nay_an_gi.service.merchant.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    IAccountService accountService;


    @GetMapping
    public ResponseEntity<Iterable<Account>> findAll() {
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.findAccountById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.save(account), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return new ResponseEntity<>(accountService.updateAccount(id,account), HttpStatus.OK);
    }

    @PatchMapping("/merchant/{id}")
    public ResponseEntity<Account> updateMerchantInfo(@PathVariable Long id, @RequestBody Account account) {
        return new ResponseEntity<>(accountService.updateAccountMerchantInfo(id,account), HttpStatus.OK);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<Account> updateUserInfo(@PathVariable Long id, @RequestBody Account account) {
        return new ResponseEntity<>(accountService.updateAccountUserInfo(id,account), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable Long id) {
        accountService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/merchant/{id}")
    public ResponseEntity<?> findAcountByMerchant(@PathVariable Long id) {
        Account accountOptional = accountService.findAccByMerchantId(id);
        return new ResponseEntity<>(new MerchantDTO(accountOptional.getMerchant().getId(), accountOptional.getEmail(), accountOptional.getMerchant().getName()), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findAcountByUser(@PathVariable Long id) {
        Account accountOptional = accountService.findAccByUserId(id);
        return new ResponseEntity<>(new MerchantDTO(accountOptional.getUser().getId(), accountOptional.getEmail(), accountOptional.getUser().getName()), HttpStatus.OK);
    }


}
