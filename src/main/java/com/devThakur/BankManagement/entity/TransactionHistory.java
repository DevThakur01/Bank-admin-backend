package com.devThakur.BankManagement.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "tran-history")
public class TransactionHistory {
    @Id
    private ObjectId id;
    private long senderAccNo;
    private long receiverAccNo;
    private double amount;
    private String type;
    private LocalDate transactionDate;

    // Constructors
    public TransactionHistory() {
    }

    public TransactionHistory(long senderAccNo, long receiverAccNo, double amount, LocalDate transactionDate) {
        this.senderAccNo = senderAccNo;
        this.receiverAccNo = receiverAccNo;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    // Getters and Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public long getSenderAccNo() {
        return senderAccNo;
    }

    public void setSenderAccNo(long senderAccNo) {
        this.senderAccNo = senderAccNo;
    }

    public long getReceiverAccNo() {
        return receiverAccNo;
    }

    public void setReceiverAccNo(long receiverAccNo) {
        this.receiverAccNo = receiverAccNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
