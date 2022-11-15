package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.model.search_form.SearchForm;
import com.example.trua_nay_an_gi.service.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class SearchProductController {

    @Autowired
    SearchProductService searchProductService;

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchForm searchForm){
        Iterable<Product> products =  searchProductService.searchByForm(searchForm);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
