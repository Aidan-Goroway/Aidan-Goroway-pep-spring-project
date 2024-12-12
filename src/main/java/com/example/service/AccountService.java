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
    public int registerAccount(Account account){
        
        if (!account.getUsername().isBlank() &&
            account.getPassword().length() >= 4 &&
            !accountRepository.existsByUsername(account.getUsername())){
                accountRepository.save(account);
                return 200; //OK
        }
        else if (accountRepository.existsByUsername(account.getUsername())){
            return 409; //CONFLICT
        }
        else{
            return 400; //OTHER
        }
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
