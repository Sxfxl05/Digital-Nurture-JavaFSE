package com.banking.controller;

import com.banking.model.Account;
import com.banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return accountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/transfer")
    @Transactional // Ensures atomic rollback on failure
    public ResponseEntity<String> transfer(@RequestParam Long fromId, @RequestParam Long toId, @RequestParam Double amount) {
        Account fromAcc = accountRepository.findById(fromId).orElse(null);
        Account toAcc = accountRepository.findById(toId).orElse(null);

        if (fromAcc == null || toAcc == null) {
            return ResponseEntity.badRequest().body("Error: One or both accounts do not exist.");
        }

        if (fromAcc.getBalance() < amount) {
            return ResponseEntity.badRequest().body("Error: Insufficient balance.");
        }

        fromAcc.setBalance(fromAcc.getBalance() - amount);
        toAcc.setBalance(toAcc.getBalance() + amount);

        accountRepository.save(fromAcc);
        accountRepository.save(toAcc);

        return ResponseEntity.ok("Successfully transferred " + amount + " from account " + fromId + " to " + toId);
    }
}