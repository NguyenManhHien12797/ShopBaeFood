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

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IMerchantService merchantService;
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

//        accountOptional.get().setPassword(encoder.encode(accountOptional.get().getPassword()));
        return new ResponseEntity<>(accountOptional.get(), HttpStatus.OK);
//        return new ResponseEntity<>(new AccountRegisterDTO(), HttpStatus.OK);
    }
    //http://localhost:8080/account
    @PostMapping()
    public ResponseEntity<Account> saveCustomer(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.save(account), HttpStatus.CREATED);
    }
    //http://localhost:8080/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateCustomer(@PathVariable Long id, @RequestBody Account account) {
        Optional<Account> accountOptional = accountService.findById(id);
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        account.setId(accountOptional.get().getId());
        return new ResponseEntity<>(accountService.save(account), HttpStatus.OK);
    }

    @PatchMapping("/merchant/{id}")
    public ResponseEntity<Account> updateMerchantInfo(@PathVariable Long id, @RequestBody Account account) {
        Optional<Account> accountOptional = accountService.findById(id);
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accountOptional.get().setUserName(account.getUserName());
        accountOptional.get().setPassword(encoder.encode(account.getPassword()));
        accountOptional.get().setEmail(account.getEmail());
        accountOptional.get().setMerchant(account.getMerchant());
        accountOptional.get().setEnabled(true);
        return new ResponseEntity<>(accountService.save(accountOptional.get()), HttpStatus.OK);
    }
    @PatchMapping("/user/{id}")
    public ResponseEntity<Account> updateUserInfo(@PathVariable Long id, @RequestBody Account account) {
        Optional<Account> accountOptional = accountService.findById(id);
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accountOptional.get().setUserName(account.getUserName());
        accountOptional.get().setPassword(encoder.encode(account.getPassword()));
        accountOptional.get().setEmail(account.getEmail());
        accountOptional.get().setUser(account.getUser());
        accountOptional.get().setEnabled(true);
        return new ResponseEntity<>(accountService.save(accountOptional.get()), HttpStatus.OK);
    }
    //    http://localhost:8080/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteCustomer(@PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findById(id);
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accountService.remove(id);
        return new ResponseEntity<>(accountOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/merchant/{id}")
    public ResponseEntity<?> findAcountByMerchant(@PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findAccByMerchantId(id);
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MerchantDTO(accountOptional.get().getMerchant().getId(),accountOptional.get().getEmail(),accountOptional.get().getMerchant().getName()), HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> findAcountByUser(@PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findAccByMerchantId(id);
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new MerchantDTO(accountOptional.get().getMerchant().getId(),accountOptional.get().getEmail(),accountOptional.get().getMerchant().getName()), HttpStatus.OK);
    }


}
