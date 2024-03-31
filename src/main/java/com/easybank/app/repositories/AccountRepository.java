package com.easybank.app.repositories;

import com.easybank.app.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
    public Account findAccountByAccountNumber(@Param("accountNumber") String accountNumber);

}