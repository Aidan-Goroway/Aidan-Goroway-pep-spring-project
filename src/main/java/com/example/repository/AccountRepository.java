package com.example.repository;

import java.util.Optional;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository <Account, Integer>{

    Optional<Account> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByAccountId(String accountId);

    boolean existsByUsernameAndPassword(String username, String password);

}
