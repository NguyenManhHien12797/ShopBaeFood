package com.example.trua_nay_an_gi.service.seviceImpl;

import com.example.trua_nay_an_gi.exception.AppUserNotFoundException;
import com.example.trua_nay_an_gi.model.Account;
import com.example.trua_nay_an_gi.model.AppUser;
import com.example.trua_nay_an_gi.model.dto.AccountRegisterDTO;
import com.example.trua_nay_an_gi.payload.response.MessageResponse;
import com.example.trua_nay_an_gi.repository.IAppUserRepository;
import com.example.trua_nay_an_gi.service.IAccountService;
import com.example.trua_nay_an_gi.service.IAppUserSevice;
import com.example.trua_nay_an_gi.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements IAppUserSevice {
    @Autowired
    IAppUserRepository appUserRepository;


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
    public AppUser findUserById(Long id) {
        return appUserRepository.findUserById(id).orElseThrow(() -> new AppUserNotFoundException(404, "AppUser by id "+id+ " was not found"));
    }

    @Override
    public AppUser updateUser(Long id, AppUser user) {
        AppUser updateUser = this.findUserById(id);
        user.setId(updateUser.getId());
        user.setAccount(updateUser.getAccount());
        return appUserRepository.save(user);
    }

    @Override
    public void saveUserToRegister(String address, String avatar, String name, String phone, Long account_id, String status) {
        appUserRepository.saveUserToRegister(address,avatar,name,phone,account_id,status);
    }
}
