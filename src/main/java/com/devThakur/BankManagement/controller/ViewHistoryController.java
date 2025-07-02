package com.devThakur.BankManagement.controller;


import com.devThakur.BankManagement.entity.TransactionHistory;
import com.devThakur.BankManagement.service.ViewHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ViewHistoryController {

    @Autowired
    ViewHistoryService viewHistoryService;
   // @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/transaction-history/{accountNumber}")
    public ResponseEntity<List<TransactionHistory>> showHistory(@PathVariable long accountNumber) {
        try {
            List<TransactionHistory> history = viewHistoryService.getTransactionHistory(accountNumber);
            return ResponseEntity.ok(history);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
