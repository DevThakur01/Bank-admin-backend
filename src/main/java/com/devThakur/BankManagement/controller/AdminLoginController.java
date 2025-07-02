package com.devThakur.BankManagement.controller;


import com.devThakur.BankManagement.dto.AdminLoginDTO;
import com.devThakur.BankManagement.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
    //@CrossOrigin(origins = "http://localhost:5173")
    public class AdminLoginController {

        @Autowired
        private AdminLoginService adminLoginService;

        @PostMapping("/admin-login")
        public ResponseEntity<Map<String, String>> login(@RequestBody AdminLoginDTO loginDTO) {
            Map<String, String> result = adminLoginService.login(loginDTO);

            if ("error".equals(result.get("status"))) {
                return ResponseEntity.status(401).body(result); // Unauthorized
            }

            return ResponseEntity.ok(result);
        }
}
