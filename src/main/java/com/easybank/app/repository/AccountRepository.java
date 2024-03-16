package com.easybank.app.repository;

import com.easybank.app.model.Account;
import com.easybank.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
    public Account findAccountByAccountNumber(@Param("accountNumber") String accountNumber);

}