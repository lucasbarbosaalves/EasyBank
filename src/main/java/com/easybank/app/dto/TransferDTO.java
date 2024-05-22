package com.easybank.app.dto;

public record TransferDTO(Long fromAccountId,
                          Long toAccountId,
                          Integer amount,
                          String transactionType,
                          String description) {
}




