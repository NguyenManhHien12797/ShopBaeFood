package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.Product;

import com.example.trua_nay_an_gi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/public/products")
    public ResponseEntity<List<Product>> finAll() {
        List<Product> products = productService.finallProductbydeleteflagTrue();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        Optional<Product> products = productService.findById(id);
        if (!products.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(products.get().getId());
        product.setMerchant(products.get().getMerchant());
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @GetMapping("/public/products/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Product> deleteProduct (@PathVariable Long id){
//        Optional<Product> productOptional = productService.findById(id);
//        if (!productOptional.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        productService.remove(id);
//        return new ResponseEntity<>(productOptional.get(),HttpStatus.NO_CONTENT);
//    }

    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        product.setNumberOrder("0");
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<Product> disableProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productOptional.get().setDeleteFlag(false);
        return new ResponseEntity<>(productService.save(productOptional.get()), HttpStatus.OK);
    }
    @GetMapping("/public/products/merchant/{id}")
    public ResponseEntity<Iterable<Product>> findAllByMerchant(@PathVariable Long id){
        List<Product> products=(List<Product>) productService.findAllByDeleteFlagAndMerchant(id);
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
    @GetMapping("/public/products/search/{id}")
    public ResponseEntity<Iterable<Product>> findAllByMerchantAndName(@PathVariable Long id,@RequestParam String name){
        List<Product> products = productService.fAllByDeleFlagTAndMerAndNameContai(id,name);
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}

