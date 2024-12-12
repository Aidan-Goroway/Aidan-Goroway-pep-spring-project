package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    // Handler 1
    public Account registerAccount(){
        
        return null;
    }

    // Handler 2
    public Account loginAccount(Account account){

        String searchUser = account.getUsername();
        String searchPass = account.getPassword();
        
        if (accountRepository.existsByUsernameAndPassword(searchUser, searchPass)){
            return accountRepository.findByUsername(searchUser).get();
        }
        else {
            return null;
        }
    }
}
