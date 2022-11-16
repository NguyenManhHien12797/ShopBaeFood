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
        Optional<Cart>optionalCart1 = cartService.findCartByProductIdAndUserId(cart.getProduct_id(), cart.getUser_id() );
        if (optionalCart1.isPresent()) {
           return ResponseEntity.ok(new MessageResponse("Co cart roi"));
        }
        cartService.saveCart(cart.getQuantity(), cart.getPrice(), cart.getUser_id(), cart.getProduct_id(), cart.getTotalPrice());

        Optional<Cart>optionalCart = cartService.findCartByProductIdAndUserId(cart.getProduct_id(), cart.getUser_id() );
        if (optionalCart.isPresent()) {
            cartService.setProductCart(optionalCart.get().getId(), cart.getProduct_id());
        }

//        cartService.save(cart);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cart/{produc_id}")
    public ResponseEntity<Cart> updateToCart(@PathVariable Long produc_id,@RequestBody Long user_id) {
        Optional<Product> productOptional = productService.findById(produc_id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Cart>cartOptional = cartService.findCartByProductIdAndUserId(productOptional.get().getId(), user_id);
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int quantity = cartOptional.get().getQuantity() + 1;
        cartService.updateQuantityCart(quantity, cartOptional.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
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
