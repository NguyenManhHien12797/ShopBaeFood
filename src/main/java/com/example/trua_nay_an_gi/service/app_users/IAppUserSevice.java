package com.example.trua_nay_an_gi.service.app_users;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.service.GeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IAppUserSevice extends GeneralService<AppUser>{
boolean existByName(String name);
    Optional<AppUser> findByUsername(String username);
}
