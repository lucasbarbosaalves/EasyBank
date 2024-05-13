package com.easybank.app.model.transactions.impl;

import com.easybank.app.model.Account;
import com.easybank.app.model.transactions.Transaction;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class TEDTransaction implements Transaction {

    @Override
    public void transfer(Account fromAccount, Account toAccount, int amount) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        int hour = now.getHour();

        if (hour >= 9 && hour <= 22) {
            if (fromAccount.getBalance() >= amount) {
                fromAccount.setBalance(fromAccount.getBalance() - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);
            } else {
                throw new RuntimeException("Saldo insuficiente na conta de origem para transferência via TED.");
            }
        } else {
            throw new RuntimeException("As transferências via TED só são permitidas entre as 09h e 22h.");
        }
    }
}
