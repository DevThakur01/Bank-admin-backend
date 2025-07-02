package com.devThakur.BankManagement.service;


import com.devThakur.BankManagement.entity.CreateAccount;
import com.devThakur.BankManagement.entity.TransactionHistory;
import com.devThakur.BankManagement.entity.Withdraw;
import com.devThakur.BankManagement.repository.CreateAccountRepo;
import com.devThakur.BankManagement.repository.TransactionHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class WithdrawService {
    @Autowired
    TransactionHistoryRepo transactionHistoryRepo;

    @Autowired
    CreateAccountRepo createAccountRepo;

    public Map<String, Object> withdraw(Withdraw withdraw) {
        // Step 1: Create and save TransactionHistory
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionDate(LocalDate.now());
        transactionHistory.setSenderAccNo(withdraw.getAccountNo());
        transactionHistory.setReceiverAccNo(withdraw.getAccountNo());
        transactionHistory.setAmount(withdraw.getBalance());
        transactionHistory.setType("Withdraw");

        TransactionHistory savedTransaction = transactionHistoryRepo.save(transactionHistory);

        // Step 2: Fetch account by account number
        CreateAccount account = createAccountRepo.findByAccountNumber(withdraw.getAccountNo());
        if (account == null) {
            throw new RuntimeException("Account not found with Account No: " + withdraw.getAccountNo());
        }

        // Optional: Check if balance is sufficient
        if (account.getBalance() < withdraw.getBalance()) {
            throw new RuntimeException("Insufficient balance for withdrawal.");
        }

        // Step 3: Update balance
        account.setBalance(account.getBalance() - withdraw.getBalance());

        // Step 4: Add transaction ID to the account's transaction list
        if (account.getTransactionIds() == null) {
            account.setTransactionIds(new ArrayList<>());
        }
        account.getTransactionIds().add(savedTransaction.getId());

        // Step 5: Save updated account
        createAccountRepo.save(account);

        // Step 6: Prepare response data
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Money withdrawn successfully");
        response.put("accountNumber", account.getAccountNumber());
        response.put("newBalance", account.getBalance());
        response.put("transactionId", savedTransaction.getId());

        return response;
    }
}

