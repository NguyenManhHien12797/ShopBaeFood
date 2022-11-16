package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.model.search_form.SearchForm;
import com.example.trua_nay_an_gi.service.SearchMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class SearchProductController {

    @Autowired
    SearchMerchantService searchMerchantService;

    @PostMapping("/public/search")
    public ResponseEntity<?> search(@RequestBody SearchForm searchForm){
        Iterable<Merchant> merchants =  searchMerchantService.searchByForm(searchForm);
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }
}
