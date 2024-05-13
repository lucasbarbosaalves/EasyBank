package com.easybank.app.controllers;

import com.easybank.app.dto.TransferDTO;
import com.easybank.app.factory.TransactionFactory;
import com.easybank.app.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody TransferDTO transferDTO) {
        try {
            transferService.setTransactionStrategy(TransactionFactory.getTransaction(transferDTO.transactionType()));
            transferService.transfer(transferDTO);
            return ResponseEntity.ok("Transferência realizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
