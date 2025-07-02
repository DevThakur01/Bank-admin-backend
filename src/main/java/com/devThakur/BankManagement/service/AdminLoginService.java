package com.devThakur.BankManagement.service;

import com.devThakur.BankManagement.dto.AdminLoginDTO;
import com.devThakur.BankManagement.entity.CreateAdmin;
import com.devThakur.BankManagement.repository.CreateAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class AdminLoginService {

    @Autowired
    CreateAdminRepo createAdminRepo;
    public Map<String, String> login(AdminLoginDTO loginDTO) {
        Map<String, String> response = new HashMap<>();

        CreateAdmin admin = createAdminRepo.findByUsername(loginDTO.getUsername());

        if (admin == null) {
            response.put("status", "error");
            response.put("message", "Username not found");
            return response;
        }

        if (!admin.getPassword().equals(loginDTO.getPassword())) {
            response.put("status", "error");
            response.put("message", "Incorrect password");
            return response;
        }

        response.put("status", "success");
        response.put("message", "Login successful");
        return response;
    }
}

