package com.devThakur.BankManagement.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Withdraw {
    private long accountNo;
    private double balance;

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
