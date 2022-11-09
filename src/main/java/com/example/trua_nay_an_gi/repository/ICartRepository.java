package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.model.product.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartByUserAndMerchant(AppUser appUser, Merchant merchant);

    Iterable<Cart> findCartByUser(AppUser appUser);
}
