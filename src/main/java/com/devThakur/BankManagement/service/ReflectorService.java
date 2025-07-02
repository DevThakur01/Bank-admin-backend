package com.devThakur.BankManagement.service;

import com.devThakur.BankManagement.entity.CreateAccount;
import com.devThakur.BankManagement.repository.CreateAccountRepo;
import com.devThakur.BankManagement.repository.CreateAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ReflectorService {



        @Autowired
        private CreateAdminRepo createAdminRepo;

        @Autowired
        private CreateAccountRepo createAccountRepo;

        public long getTotalAccounts() {
            return createAccountRepo.count();
        }

        public long getTotalAdmins() {
            return createAdminRepo.count();
        }

        public double getTotalCash() {
            List<CreateAccount> accounts = createAccountRepo.findAllBalances();
            return accounts.stream()
                    .mapToDouble(CreateAccount::getBalance)
                    .sum();
        }
    }


