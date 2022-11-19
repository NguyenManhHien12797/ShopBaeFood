package com.example.trua_nay_an_gi.service.seviceImpl;

import com.example.trua_nay_an_gi.exception.AccountNotFoundException;
import com.example.trua_nay_an_gi.model.Account;
import com.example.trua_nay_an_gi.repository.IAccountRepository;
import com.example.trua_nay_an_gi.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService, UserDetailsService {
    @Autowired
    IAccountRepository accountRepository;

    @Override
    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void remove(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void removeAll() {

    }

    @Override
    public Account findByName(String name) {
        return accountRepository.findByUserName(name);
    }

    @Override
    public Long findIdUserByUserName(String username) {
        return accountRepository.findIdUserByUserName(username);
    }

    @Override
    public boolean existsAccountByUserName(String username) {
        return accountRepository.existsAccountByUserName(username);
    }

    @Override
    public Account findAccByMerchantId(Long id) {
        return accountRepository.findAccByMerchantId(id).orElseThrow(() -> new AccountNotFoundException(404, "Account by id "+ id + " was not found"));
    }

    @Override
    public Account findAccByUserId(Long id) {
        return accountRepository.findAccByUserId(id).orElseThrow(() -> new AccountNotFoundException(404,"Account by id "+ id + " was not found"));
    }

    @Override
    public Account findAccountById(Long id) {
        return accountRepository.findAccountById(id).orElseThrow(() -> new AccountNotFoundException(404, "Account by id "+ id + " was not found"));
    }

    @Override
    public Account updateAccountUserInfo(Long id, Account account) {
        Account updateAccount = this.findAccountById(id);
        updateAccount.setUserName(account.getUserName());
        updateAccount.setEmail(account.getEmail());
        updateAccount.setUser(account.getUser());
        updateAccount.setEnabled(true);
        return accountRepository.save(updateAccount);
    }

    @Override
    public Account updateAccountMerchantInfo(Long id, Account account) {
        Account updateAccount = this.findAccountById(id);
        updateAccount.setUserName(account.getUserName());
        updateAccount.setEmail(account.getEmail());
        updateAccount.setMerchant(account.getMerchant());
        updateAccount.setEnabled(true);
        return accountRepository.save(updateAccount);
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        Account updatAccount = this.findAccountById(id);
        account.setId(updatAccount.getId());
        return accountRepository.save(account);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(username);
        return AccountDetails.build(account);
    }

}
