package com.devThakur.BankManagement.service;

import com.devThakur.BankManagement.dto.MemberDTO;
import com.devThakur.BankManagement.repository.CreateAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberService {

    @Autowired
    CreateAccountRepo createAccountRepo;

    public List<MemberDTO> getAllMembers() {
        return createAccountRepo.findAll()
                .stream()
                .map(account -> new MemberDTO(
                        account.getAccountNumber(),
                        account.getFirstname(),
                        account.getLastname(),
                        account.getEmail(),
                        account.getBalance()
                ))
                .toList();
    }

}
