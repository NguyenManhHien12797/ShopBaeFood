package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.model.Cart;
import com.example.trua_nay_an_gi.model.Product;
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

    @Query(value = "select * from cart where product_id = ?1 and user_id= ?2", nativeQuery = true)
    Optional<Cart> findCartByProductIdAndUserId(Long product_id, Long user_id);
    boolean existsCartByProductId(Long id);

    @Modifying
    @Query(value = "insert into product_cart(cart_id,product_id) values (?1,?2)", nativeQuery = true)
    void setProductCart(Long cart_id, Long product_id);

    @Modifying
    @Query(value = "update cart set quantity = ?1 where id=?2", nativeQuery = true)
    void updateQuantityCart(int quantity,Long cart_id);

    void deleteCartsByUser(AppUser user);
}
