package com.devThakur.BankManagement.service;

import com.devThakur.BankManagement.entity.CreateAccount;
import com.devThakur.BankManagement.entity.Deposit;
import com.devThakur.BankManagement.entity.TransactionHistory;
import com.devThakur.BankManagement.repository.CreateAccountRepo;
import com.devThakur.BankManagement.repository.TransactionHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepositService {

    @Autowired
    private TransactionHistoryRepo transactionHistoryRepo;

    @Autowired
    private CreateAccountRepo createAccountRepo;

    public Map<String, Object> deposit(Deposit deposit) {
        // Step 1: Create and save TransactionHistory
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionDate(LocalDate.now());
        transactionHistory.setSenderAccNo(deposit.getAccountNo());
        transactionHistory.setReceiverAccNo(deposit.getAccountNo());
        transactionHistory.setAmount(deposit.getBalance());
        transactionHistory.setType("Deposit");

        TransactionHistory savedTransaction = transactionHistoryRepo.save(transactionHistory);

        // Step 2: Fetch account by account number
        CreateAccount account = createAccountRepo.findByAccountNumber(deposit.getAccountNo());
        if (account == null) {
            throw new RuntimeException("Account not found with Account No: " + deposit.getAccountNo());
        }

        // Step 3: Update balance
        account.setBalance(account.getBalance() + deposit.getBalance());

        // Step 4: Add transaction ID
        if (account.getTransactionIds() == null) {
            account.setTransactionIds(new ArrayList<>());
        }
        account.getTransactionIds().add(savedTransaction.getId());

        // Step 5: Save updated account
        createAccountRepo.save(account);

        // Return response data
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Money deposited successfully");
        response.put("accountNumber", account.getAccountNumber());
        response.put("newBalance", account.getBalance());
        response.put("transactionId", savedTransaction.getId().toHexString());

        return response;
    }

}
