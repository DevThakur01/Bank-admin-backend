package com.devThakur.BankManagement.controller;


import com.devThakur.BankManagement.service.ReflectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ReflectorController {

        @Autowired
        private ReflectorService reflectorService;
//@CrossOrigin(origins = "http://localhost:5173")
        @GetMapping("/summary")
        public ResponseEntity<Map<String, Object>> getSummary() {
            Map<String, Object> summary = new HashMap<>();
            summary.put("totalAccounts", reflectorService.getTotalAccounts());
            summary.put("totalAdmins", reflectorService.getTotalAdmins());
            summary.put("totalCash", reflectorService.getTotalCash());

            return ResponseEntity.ok(summary);
        }
    }



