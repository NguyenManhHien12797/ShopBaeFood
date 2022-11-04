package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.app_users.Account;
import com.example.trua_nay_an_gi.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountService accountService;
    //http://localhost:8080/account
    @GetMapping
    public ResponseEntity<Iterable<Account>> findAll() {
        List<Account> cuaccounts = (List<Account>) accountService.findAll();
        if (cuaccounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cuaccounts, HttpStatus.OK);
    }
    //http://localhost:8080/account/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findById(id);
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountOptional.get(), HttpStatus.OK);
    }
    //http://localhost:8080/account/create
    @PostMapping("create")
    public ResponseEntity<Account> saveCustomer(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.save(account), HttpStatus.CREATED);
    }
    //http://localhost:8080/edit/{id}
    @PutMapping("/edit/{id}")
    public ResponseEntity<Account> updateCustomer(@PathVariable Long id, @RequestBody Account account) {
        Optional<Account> accountOptional = accountService.findById(id);
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        account.setId(accountOptional.get().getId());
        return new ResponseEntity<>(accountService.save(account), HttpStatus.OK);
    }
    //    http://localhost:8080/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Account> deleteCustomer(@PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findById(id);
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accountService.remove(id);
        return new ResponseEntity<>(accountOptional.get(), HttpStatus.NO_CONTENT);
    }
}
