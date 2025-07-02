package com.devThakur.BankManagement.service;

import com.devThakur.BankManagement.entity.CreateAdmin;
import com.devThakur.BankManagement.repository.CreateAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CreateAdminService {
    @Autowired
    CreateAdminRepo createAdminRepo;

    public void saveAdmin(CreateAdmin admin){
        createAdminRepo.save(admin);

    }

    public Boolean validAdmin(String username,String password){
        CreateAdmin admin=createAdminRepo.findByUsernameAndPassword(username, password);
        return admin !=null;
    }

}
