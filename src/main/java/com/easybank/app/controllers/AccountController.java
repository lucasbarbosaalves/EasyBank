package com.easybank.app.controllers;

import com.easybank.app.dto.AccountDTO;
import com.easybank.app.model.Account;
import com.easybank.app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountDetails(@PathVariable Long id) {
        Account account = accountService.getAccountDetails(id);
        AccountDTO accountDTO = new AccountDTO(account.getAccountNumber(), account.getBalance(), account.getUser());
        return ResponseEntity.ok(accountDTO);
    }


    @PostMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestBody Double amount) {
        Account account = accountService.deposit(id, amount);
        return ResponseEntity.ok(account);
    }
}