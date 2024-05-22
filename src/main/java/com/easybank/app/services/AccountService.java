package com.easybank.app.services;

import com.easybank.app.model.Account;
import com.easybank.app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Account getAccountDetails(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public BigInteger generateAccountNumber() {
        BigInteger accountNumber;
        Random random = new Random();
        do {
            accountNumber = new BigInteger(7, random);
        } while (accountRepository.findByAccountNumber(accountNumber) != null);

        return accountNumber;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Account deposit(Long accountId, Double amount) {
        Account account = getAccountDetails(accountId);
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }
}
