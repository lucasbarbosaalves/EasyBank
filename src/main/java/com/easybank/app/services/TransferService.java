package com.easybank.app.services;

import com.easybank.app.dto.TransferDTO;
import com.easybank.app.factory.TransactionFactory;
import com.easybank.app.model.Account;
import com.easybank.app.model.transactions.Transaction;
import com.easybank.app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    @Autowired
    private AccountRepository accountRepository;

    private Transaction transaction;

    public void transfer(TransferDTO transferDTO) {
        Transaction transaction = TransactionFactory.getTransaction(transferDTO.transactionType());
        Account fromAccount = accountRepository.findById(transferDTO.fromAccountId())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));
        Account toAccount = accountRepository.findById(transferDTO.toAccountId())
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

        transaction.transfer(fromAccount, toAccount, transferDTO.amount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    public void setTransactionStrategy(Transaction transaction) {
        this.transaction = transaction;
    }
}
