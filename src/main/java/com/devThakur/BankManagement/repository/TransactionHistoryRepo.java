package com.devThakur.BankManagement.repository;

import com.devThakur.BankManagement.entity.TransactionHistory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionHistoryRepo extends MongoRepository<TransactionHistory, ObjectId> {
}
