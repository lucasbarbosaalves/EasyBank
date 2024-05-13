package com.easybank.app.model.transactions.impl;

import com.easybank.app.model.Account;
import com.easybank.app.model.transactions.Transaction;

public class PixTransaction implements Transaction {

    @Override
    public void transfer(Account fromAccount, Account toAccount, int amount) {
        if (fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
        } else {
            throw new RuntimeException("Saldo insuficiente na conta de origem para transferência via Pix.");
        }
    }
}