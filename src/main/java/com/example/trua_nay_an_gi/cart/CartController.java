package com.example.trua_nay_an_gi.cart;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.dto.cart.CartDto;
import com.example.trua_nay_an_gi.model.product.Cart;
import com.example.trua_nay_an_gi.model.product.ErrorMessage;
import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.service.account.product.IProductService;
import com.example.trua_nay_an_gi.service.app_users.IAppUserSevice;
import com.example.trua_nay_an_gi.service.cart.ICartService;
import com.example.trua_nay_an_gi.service.merchant.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    IAppUserSevice appUserSevice;

    @Autowired
    IMerchantService merchantService;

    @Autowired
    ICartService cartService;

    @Autowired
    IProductService productService;

    public ResponseEntity<?> changeProductQuantity(Long cartId, Long productId, int amount) {
        Optional<Cart> findCart = cartService.findById(cartId);
        if (!findCart.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Product> findProduct = productService.findById(productId);
        if (!findProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Cart cart = findCart.get();
        Product product = findProduct.get();

        boolean result = cartService.changeProductQuantityInCart(cart, product, amount);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/{cartId}/increase-product-quantity/{productId}")
    public ResponseEntity<?> increaseProductQuantityInCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return changeProductQuantity(cartId,productId, 1);
    }


    @GetMapping("/{cartId}/decrease-product-quantity/{productId}")
    public ResponseEntity<?> decreaseProductQuantityInCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return changeProductQuantity(cartId, productId , -1);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getAllCartDtoByUser(@PathVariable Long userId) {
        Optional<AppUser> findUser = appUserSevice.findById(userId);
        if (!findUser.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Người dùng không tồn tại");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        List<CartDto> cartDtos = cartService.getAllCartDtoByUser(findUser.get());
        return new ResponseEntity<>(cartDtos, HttpStatus.OK);
    }
}
