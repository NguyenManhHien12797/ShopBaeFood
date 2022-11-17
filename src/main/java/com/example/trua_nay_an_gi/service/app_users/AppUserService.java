package com.example.trua_nay_an_gi.service.app_users;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService implements IAppUserSevice {
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public Iterable<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return appUserRepository.findById(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public void remove(Long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public void removeAll() {

    }


    @Override
    public boolean existByName(String name) {
        return appUserRepository.existsByName(name);
    }

    @Override
    public AppUser findByUserName(String name) {
        return appUserRepository.findByName(name);
    }

    @Override
    public void saveUserToRegister(String address, String avatar, String name, String phone, Long account_id,String status) {
        appUserRepository.saveUserToRegister(address,avatar,name,phone,account_id,status);
    }
}
