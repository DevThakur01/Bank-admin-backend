package com.devThakur.BankManagement.controller;

import com.devThakur.BankManagement.entity.CreateAccount;
import com.devThakur.BankManagement.service.CreateAccountService;
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
public class CreateAccountController {

    @Autowired
    CreateAccountService createAccountService;
  //  @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/create-account")
    public ResponseEntity<Map<String, Object>> saveAccount(@RequestBody CreateAccount account) {
        Map<String, Object> response = new HashMap<>();
        try {
            long accNo = createAccountService.saveAccount(account);
            response.put("message", "Account created successfully");
            response.put("accountNumber", accNo);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("error", "Failed to create account");
            response.put("details", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
