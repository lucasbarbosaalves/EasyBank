package com.easybank.app.model.transactions;

import com.easybank.app.model.Account;

public interface Transaction {
    void transfer(Account fromAccount, Account toAccount, int amount);
}
