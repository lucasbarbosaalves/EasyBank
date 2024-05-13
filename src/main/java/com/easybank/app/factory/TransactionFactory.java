package com.easybank.app.factory;

import com.easybank.app.model.transactions.Transaction;
import com.easybank.app.model.transactions.impl.BankSlipTransaction;
import com.easybank.app.model.transactions.impl.PixTransaction;
import com.easybank.app.model.transactions.impl.TEDTransaction;

public class TransactionFactory {

    public static Transaction getTransaction(String transactionType) {
        return switch (transactionType.toUpperCase()) {
            case "PIX" -> new PixTransaction();
            case "BOLETO" -> new BankSlipTransaction();
            case "TED" -> new TEDTransaction();
            default -> throw new IllegalArgumentException("Tipo de transação inválido: " + transactionType);
        };
    }
}
