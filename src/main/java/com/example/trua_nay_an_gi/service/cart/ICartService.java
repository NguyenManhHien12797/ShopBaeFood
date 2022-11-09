package com.example.trua_nay_an_gi.service.cart;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.model.dto.cart.CartDetailDto;
import com.example.trua_nay_an_gi.model.dto.cart.CartDto;
import com.example.trua_nay_an_gi.model.product.Cart;
import com.example.trua_nay_an_gi.model.product.Product;
import com.example.trua_nay_an_gi.service.GeneralService;

import java.util.List;
import java.util.Optional;

public interface ICartService extends GeneralService<Cart> {
    Optional<Cart> findCartByUserAndMerchant(AppUser appUser, Merchant merchant);

    Iterable<Cart> findAllCartByUser(AppUser appUser);

    Cart createCartWithUserAndMerchant(AppUser appUser,Merchant merchant);

    CartDto getCartDtoByUserAndMerchant(AppUser appUser,Merchant merchant);

    List<CartDto> getAllCartDtoByUser(AppUser appUser);

    CartDto addProductToCart(AppUser appUser, CartDetailDto cartDetailDto);

    boolean changeProductQuantityInCart(Cart cart, Product product,int amount);

    void emptyCartById(Long cartId);
}
