package com.devThakur.BankManagement.controller;

import com.devThakur.BankManagement.entity.Deposit;
import com.devThakur.BankManagement.entity.Withdraw;
import com.devThakur.BankManagement.service.DepositService;
import com.devThakur.BankManagement.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WithdrawController {
        @Autowired
        WithdrawService withdrawService;
   // @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/withdraw")
    public ResponseEntity<Map<String, Object>> withdraw(@RequestBody Withdraw withdraw) {
        try {
            Map<String, Object> result = withdrawService.withdraw(withdraw);
            return ResponseEntity.ok(result);
        } catch (RuntimeException ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Withdraw Failed");
            error.put("details", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

}


