package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.AppUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface IAppUserRepository extends PagingAndSortingRepository<AppUser,Long> {
 boolean existsByName(String name);
 Optional<AppUser> findByName(String name);
 Optional<AppUser> findUserById(Long id);


 @Modifying
 @Transactional
 @Query(value = "insert into app_user(address,avatar,name,phone,account_id,status) values (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
 void saveUserToRegister(String address, String avatar, String name, String phone, Long account_id, String status);
}
