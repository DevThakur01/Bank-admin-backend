package com.devThakur.BankManagement.controller;

import com.devThakur.BankManagement.entity.TransactionHistory;
import com.devThakur.BankManagement.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TransferController {
    @Autowired
    TransferService transferservice;
    //@CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/transfer")
    public ResponseEntity<Map<String, String>> transfer(@RequestBody TransactionHistory transactionHistory) {
        Map<String, String> response = transferservice.transferMoney(transactionHistory);

        if (response.containsKey("error")) {
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }

        return ResponseEntity
                .ok()
                .body(response);
    }

}
