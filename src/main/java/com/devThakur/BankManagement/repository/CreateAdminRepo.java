package com.devThakur.BankManagement.repository;

import com.devThakur.BankManagement.entity.CreateAdmin;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreateAdminRepo extends MongoRepository<CreateAdmin, ObjectId> {
    CreateAdmin findByUsernameAndPassword(String Username,String Password);
    CreateAdmin findByUsername(String Username);

    long count();  // Total number of admins
}
