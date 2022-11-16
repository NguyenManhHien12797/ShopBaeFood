package com.example.trua_nay_an_gi.controller.cart;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.model.dto.CartDTO;
import com.example.trua_nay_an_gi.model.product.Cart;
import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.payload.response.MessageResponse;
import com.example.trua_nay_an_gi.service.account.product.IProductService;
import com.example.trua_nay_an_gi.service.app_users.IAppUserSevice;
import com.example.trua_nay_an_gi.service.cart.ICartService;

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

    @Autowired
    private IAppUserSevice userSevice;

    @Autowired
    private IProductService productService;

    @GetMapping("/public/cart")
    public ResponseEntity<Iterable<Cart>> findAllCart() {
        Iterable<Cart> carts = cartService.findAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PutMapping("/cart/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        Optional<Cart> cartOptional = cartService.findById(id);
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cart.setId(cartOptional.get().getId());
        return new ResponseEntity<>(cartService.save(cart), HttpStatus.OK);
    }

    @GetMapping("/public/cart/{id}")
    public ResponseEntity<Cart> findById(@PathVariable Long id) {
        Optional<Cart> cartOptional = cartService.findById(id);
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/public/cart/user/{id}")
    public ResponseEntity<?> findAllByUserId(@PathVariable Long id) {
        Optional<AppUser> appUser = userSevice.findById(id);
        Iterable<Cart> cartOptional = cartService.findAllByUser(appUser.get());

        return new ResponseEntity<>(cartOptional, HttpStatus.OK);
    }

    @GetMapping("/public/cart/product/{id}")
    public ResponseEntity<?> findCartByProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Cart> cartOptional = cartService.findCartByProduct(productOptional.get());
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cartOptional, HttpStatus.OK);
    }


    @GetMapping("/public/cart/product/exist/{id}")
    public ResponseEntity<?> existsCartByProductId(@PathVariable Long id) {
        boolean existsCartByProductId = cartService.existsCartByProductId(id);

        return new ResponseEntity<>(existsCartByProductId, HttpStatus.OK);
    }


    @PostMapping("/cart")
    public ResponseEntity<?> saveCart(@RequestBody CartDTO cart) {
        Optional<Product> productOptional = productService.findById(cart.getProduct_id());
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        cartService.saveCart(cart.getQuantity(), cart.getPrice(), cart.getUser_id(), cart.getProduct_id(), cart.getTotalPrice());

//        cartService.save(cart);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/cart/{id}")
    public ResponseEntity<Cart> updateToCart(@PathVariable Long id, @RequestBody Cart cart) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Cart> cartOptional = cartService.findById(cart.getId());
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartOptional.get().setQuantity(cartOptional.get().getQuantity() + 1);
        return new ResponseEntity<>(cartService.save(cartOptional.get()), HttpStatus.OK);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable Long id) {
        Optional<Cart> cartOptional = cartService.findById(id);
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartService.remove(id);
        return new ResponseEntity<>(cartOptional.get(), HttpStatus.NO_CONTENT);
    }

}
