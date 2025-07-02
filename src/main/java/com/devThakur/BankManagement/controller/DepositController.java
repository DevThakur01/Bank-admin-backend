package com.devThakur.BankManagement.controller;

import com.devThakur.BankManagement.entity.Deposit;
import com.devThakur.BankManagement.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class DepositController {
    @Autowired
    DepositService depositService;

  //  @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Deposit deposit) {
        try {
            Map<String, Object> result = depositService.deposit(deposit);
            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "error", "Deposit failed",
                    "details", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "error", "Unexpected error during deposit",
                    "details", e.getMessage()
            ));
        }
    }
}




