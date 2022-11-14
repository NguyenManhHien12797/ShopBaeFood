package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AppUserRepository extends PagingAndSortingRepository<AppUser,Long> {
 boolean existsByName(String name);
 AppUser findByName(String name);

 @Modifying
 @Transactional
 @Query(value = "insert into app_user(address,avatar,name,phone,account_id) values (?1,?2,?3,?4,?5)", nativeQuery = true)
 void saveUserToRegister(String address, String avatar, String name, String phone, Long account_id);
}
