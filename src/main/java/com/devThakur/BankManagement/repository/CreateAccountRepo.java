package com.devThakur.BankManagement.repository;

import com.devThakur.BankManagement.entity.CreateAccount;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface CreateAccountRepo extends MongoRepository<CreateAccount, ObjectId> {
    CreateAccount findTopByOrderByAccountNumberDesc();
    CreateAccount findByAccountNumber(long accountNumber);

    long count();  // Total number of accounts

    @Query(value = "{}", fields = "{balance: 1}")
    List<CreateAccount> findAllBalances(); // To calculate total balance manually

}
