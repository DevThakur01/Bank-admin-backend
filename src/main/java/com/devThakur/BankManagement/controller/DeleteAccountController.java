package com.devThakur.BankManagement.controller;

import com.devThakur.BankManagement.dto.DeleteAccountDTO;
import com.devThakur.BankManagement.service.DeleteAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Map;


@RestController
public class DeleteAccountController {

        @Autowired
        private DeleteAccountService deleteAccountService;
  //  @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/delete-account")
    public ResponseEntity<Map<String, String>> deleteAccount(@RequestBody DeleteAccountDTO account) {
        Map<String, String> result = deleteAccountService.deleteAccount(account);

        if ("error".equals(result.get("status"))) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }


}



