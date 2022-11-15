package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    @Query(value = "select * from Product as p where p.delete_flag = true and p.merchant_id = ?1", nativeQuery = true)
    Iterable<Product> getAllByDeleteFlagTrueAndMerchant(Long id);
    @Query(value = "select * from Product as p where p.delete_flag = true", nativeQuery = true)
    List<Product> finallProductdeleteflagTrue();

    @Query(value = "select * from Product p where p.delete_flag = true and p.merchant_id = ?1 and p.name like concat('%', ?2, '%')", nativeQuery = true)
    List<Product> fAllByDeleFlagTAndMerAndNameContai(Long id,String name);

    @Query(value = "select * from product limit :limit", nativeQuery = true)
    Iterable<Product> findAllProduct(@Param(value = "limit") int limit);

    @Query(value = "select * from product where name like :namePattern limit :limit", nativeQuery = true)
    Iterable<Product> findAllProductWithName(@Param(value = "namePattern") String namePattern, @Param(value = "limit") int limit);

    @Query(value = "select product.* from product join product_category pc on product.id = pc.product_id " +
            "where category_id in (:categoryIdList) limit :limit offset 0", nativeQuery = true)
    Iterable<Product> findProductByCategoryIdList(
            @Param(value = "categoryIdList") String categoryIdList,
            @Param(value = "limit") int limit);

    @Query(value = "select product.* from product join product_category pc on product.id = pc.product_id " +
            "where category_id in (:categoryIdList) and product.name like :namePattern  limit :limit offset 0", nativeQuery = true)
    Iterable<Product> findProductByNameAndCategoryIdList(
            @Param(value = "namePattern") String namePattern, @Param(value = "categoryIdList") String categoryIdList,
            @Param(value = "limit") int limit);

    @Query(value = "select distinct product.* " +
            "from product join product_category p on product.id = p.product_id " +
            "where p.category_id in ( " +
            "    select pc.category_id " +
            "    from product_category pc " +
            "    where pc.product_id = :productId) " +
            "and product.id != :productId " +
            "order by product.sold desc " +
            "limit :limit", nativeQuery = true)
    Iterable<Product> findProductWithSameCategoryWith(@Param(value = "productId") Long productId, @Param(value = "limit") int limit);
}
