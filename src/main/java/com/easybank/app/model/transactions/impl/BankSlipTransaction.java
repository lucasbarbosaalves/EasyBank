package com.easybank.app.model.transactions.impl;

import com.easybank.app.model.Account;
import com.easybank.app.model.transactions.Transaction;

import java.util.concurrent.TimeUnit;

public class BankSlipTransaction implements Transaction {

    @Override
    public void transfer(Account fromAccount, Account toAccount, int amount) {
        if (fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException("Erro ao simular atraso no processamento do boleto.", e);
            }
            toAccount.setBalance(toAccount.getBalance() + amount);
        } else {
            throw new RuntimeException("Saldo insuficiente na conta de origem para transferência via boleto.");
        }
    }
}
