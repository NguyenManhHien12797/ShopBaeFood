package com.example.trua_nay_an_gi.controller;

import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.model.dto.CartDTO;
import com.example.trua_nay_an_gi.model.Cart;
import com.example.trua_nay_an_gi.model.Product;
import com.example.trua_nay_an_gi.payload.response.MessageResponse;
import com.example.trua_nay_an_gi.service.IAppUserSevice;
import com.example.trua_nay_an_gi.service.ICartService;

import com.example.trua_nay_an_gi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("/public/cart")
    public ResponseEntity<Iterable<Cart>> findAllCart() {
        return new ResponseEntity<>(cartService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/cart/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.updateCart(id, cart), HttpStatus.OK);
    }

    @GetMapping("/public/cart/{id}")
    public ResponseEntity<Cart> findById(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findCartById(id), HttpStatus.OK);
    }

    @GetMapping("/public/cart/user/{id}")
    public ResponseEntity<?> findAllByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findAllByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/public/cart/product/{id}")
    public ResponseEntity<?> findCartByProduct(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findCartByProduct(id), HttpStatus.OK);
    }


    @GetMapping("/public/cart/product/exist/{id}")
    public ResponseEntity<?> existsCartByProductId(@PathVariable Long id) {
        boolean existsCartByProductId = cartService.existsCartByProductId(id);

        return new ResponseEntity<>(existsCartByProductId, HttpStatus.OK);
    }


    @PostMapping("/cart")
    public ResponseEntity<?> saveCart(@RequestBody CartDTO cart) {
        Optional<Cart> optionalCart1 = cartService.findCartByProductIdAndUserId(cart.getProduct_id(), cart.getUser_id());
        if (optionalCart1.isPresent()) {
            return ResponseEntity.ok(new MessageResponse("Co cart roi"));
        }
        cartService.saveCart(cart.getQuantity(), cart.getPrice(), cart.getUser_id(), cart.getProduct_id(), cart.getTotalPrice());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cart/{product_id}")
    public ResponseEntity<Cart> updateToCart(@PathVariable Long product_id, @RequestBody Long user_id) {
        cartService.updateQuantityCart(product_id,user_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable Long id) {
        cartService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/cart/user/{user_id}")
    public ResponseEntity<?> deleteAllCartByUser(@PathVariable Long user_id) {
        cartService.deleteCartsByUser(user_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
