package com.devThakur.BankManagement.service;


import com.devThakur.BankManagement.entity.CreateAccount;
import com.devThakur.BankManagement.entity.TransactionHistory;
import com.devThakur.BankManagement.repository.CreateAccountRepo;
import com.devThakur.BankManagement.repository.TransactionHistoryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ViewHistoryService {

    @Autowired
    private CreateAccountRepo createAccountRepo;

    @Autowired
    private TransactionHistoryRepo transactionHistoryRepo;

    public List<TransactionHistory> getTransactionHistory(long accountNumber) {
        CreateAccount account = createAccountRepo.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new RuntimeException("Account not found.");
        }

        List<ObjectId> transactionIds = account.getTransactionIds();
        if (transactionIds == null || transactionIds.isEmpty()) {
            return new ArrayList<>();  // No history
        }

        return transactionHistoryRepo.findAllById(transactionIds);
    }
}
