package com.devThakur.BankManagement.service;

import com.devThakur.BankManagement.entity.CreateAccount;
import com.devThakur.BankManagement.repository.CreateAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountService {
    @Autowired
    CreateAccountRepo createAccountRepo;
    public long saveAccount(CreateAccount account) {
        CreateAccount prevAccount = createAccountRepo.findTopByOrderByAccountNumberDesc();
        long accNo = 10000;

        if (prevAccount != null) {
            accNo = prevAccount.getAccountNumber() + 1;
        }

        account.setAccountNumber(accNo);
        account.setBalance(0);
        createAccountRepo.save(account);

        return accNo;
    }



}



