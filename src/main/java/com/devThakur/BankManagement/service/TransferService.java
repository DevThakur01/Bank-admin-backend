package com.devThakur.BankManagement.service;


import com.devThakur.BankManagement.entity.CreateAccount;
import com.devThakur.BankManagement.entity.TransactionHistory;
import com.devThakur.BankManagement.repository.CreateAccountRepo;
import com.devThakur.BankManagement.repository.TransactionHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class TransferService {

    @Autowired
    private TransactionHistoryRepo transactionHistoryRepo;

    @Autowired
    private CreateAccountRepo createAccountRepo;


    public Map<String, String> transferMoney(TransactionHistory transferRequest) {
        long senderAccNo = transferRequest.getSenderAccNo();
        long receiverAccNo = transferRequest.getReceiverAccNo();
        double amount = transferRequest.getAmount();

        Map<String, String> response = new HashMap<>();

        // Fetch sender and receiver
        CreateAccount sender = createAccountRepo.findByAccountNumber(senderAccNo);
        CreateAccount receiver = createAccountRepo.findByAccountNumber(receiverAccNo);

        if (sender == null || receiver == null) {
            response.put("error", "Sender or Receiver account not found.");
            return response;
        }

        // Check if sender has sufficient balance
        if (sender.getBalance() < amount) {
            response.put("error", "Insufficient balance in sender's account.");
            return response;
        }

        // Update balances
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        // Create transaction entry
        TransactionHistory history = new TransactionHistory();
        history.setSenderAccNo(senderAccNo);
        history.setReceiverAccNo(receiverAccNo);
        history.setAmount(amount);
        history.setTransactionDate(LocalDate.now());
        history.setType("Transfer");

        TransactionHistory savedTransaction = transactionHistoryRepo.save(history);

        // Add transaction ID to both accounts
        if (sender.getTransactionIds() == null) {
            sender.setTransactionIds(new ArrayList<>());
        }
        if (receiver.getTransactionIds() == null) {
            receiver.setTransactionIds(new ArrayList<>());
        }

        sender.getTransactionIds().add(savedTransaction.getId());
        receiver.getTransactionIds().add(savedTransaction.getId());

        // Save updated accounts
        createAccountRepo.save(sender);
        createAccountRepo.save(receiver);

        // Prepare success response
        response.put("message", "Transfer successful");
        response.put("senderAccNo", String.valueOf(senderAccNo));
        response.put("receiverAccNo", String.valueOf(receiverAccNo));
        response.put("amount", String.valueOf(amount));
        response.put("transactionId", savedTransaction.getId().toHexString());

        return response;
    }
}


