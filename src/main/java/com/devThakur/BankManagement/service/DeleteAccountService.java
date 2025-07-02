package com.devThakur.BankManagement.service;


import com.devThakur.BankManagement.dto.DeleteAccountDTO;
import com.devThakur.BankManagement.entity.CreateAccount;
import com.devThakur.BankManagement.entity.TransactionHistory;
import com.devThakur.BankManagement.repository.CreateAccountRepo;
import com.devThakur.BankManagement.repository.TransactionHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DeleteAccountService {

    @Autowired
        CreateAccountRepo createAccountRepo;

    @Autowired
    private TransactionHistoryRepo transactionHistoryRepo;

    public Map<String, String> deleteAccount(DeleteAccountDTO deleteAccountDTO) {
        long accountNumber = deleteAccountDTO.getAccountNumber();

        CreateAccount account = createAccountRepo.findByAccountNumber(accountNumber);
        Map<String, String> response = new HashMap<>();

        if (account == null) {
            response.put("status", "error");
            response.put("message", "Account not found with Account Number: " + accountNumber);
            return response;
        }

        // Delete only deposit/withdraw transactions
        if (account.getTransactionIds() != null && !account.getTransactionIds().isEmpty()) {
            List<TransactionHistory> histories = transactionHistoryRepo.findAllById(account.getTransactionIds());

            for (TransactionHistory history : histories) {
                String type = history.getType();
                if ("Deposit".equalsIgnoreCase(type) || "Withdraw".equalsIgnoreCase(type)) {
                    transactionHistoryRepo.deleteById(history.getId());
                }
            }
        }

        // Delete account
        createAccountRepo.delete(account);

        response.put("status", "success");
        response.put("message", "Account deleted successfully along with Deposit/Withdraw transactions.");
        response.put("accountNumber", String.valueOf(accountNumber));

        return response;
    }
}



