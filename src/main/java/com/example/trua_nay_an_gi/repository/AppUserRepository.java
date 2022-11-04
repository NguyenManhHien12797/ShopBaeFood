package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends PagingAndSortingRepository<AppUser,Long> {
 boolean existsByName(String name);
 AppUser findByName(String name);
}
