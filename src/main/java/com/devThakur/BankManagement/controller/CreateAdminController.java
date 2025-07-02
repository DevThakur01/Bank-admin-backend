package com.devThakur.BankManagement.controller;


import com.devThakur.BankManagement.entity.CreateAdmin;
import com.devThakur.BankManagement.service.CreateAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

public class CreateAdminController {
    @Autowired
    CreateAdminService createAdminService;
  //  @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/create-admin")
    public ResponseEntity<Map<String, String>> saveAdmin(@RequestBody CreateAdmin admin) {
        try {
            createAdminService.saveAdmin(admin);
            return ResponseEntity.ok(Map.of("message", "Admin created successfully!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to create admin", "details", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public String validAdmin(@RequestBody CreateAdmin admin){
        boolean valid= createAdminService.validAdmin(admin.getUsername(),admin.getPassword());
        if(valid){
            return "A valid Admin";
        }
        else{
            return "Invalid Admin";
        }

    }


}
