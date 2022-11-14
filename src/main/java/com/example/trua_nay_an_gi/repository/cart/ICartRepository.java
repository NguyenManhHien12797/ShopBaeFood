package com.example.trua_nay_an_gi.repository.cart;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.product.Cart;
import com.example.trua_nay_an_gi.model.product.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ICartRepository extends PagingAndSortingRepository<Cart, Long> {

    Iterable<Cart> findAllByUser(AppUser user);

    @Modifying
    @Transactional
    @Query(value = "insert into cart(quantity,price,user_id,product_id,total_price) values (?1,?2,?3,?4,?5);", nativeQuery = true)
    void saveCart(int quantity, double price, Long userID, Long productId, double totalPrice);

    Optional<Cart> findCartByProduct(Product product);

}
