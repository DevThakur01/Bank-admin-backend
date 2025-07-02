package com.devThakur.BankManagement.dto;

public class MemberDTO {
        private long accountNumber;
        private String firstname;
        private String lastname;
        private String email;
        private double balance;

        // Constructor
        public MemberDTO(long accountNumber, String firstname, String lastname, String email, double balance) {
            this.accountNumber = accountNumber;
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.balance = balance;
        }

        // Getters and Setters
        public long getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }
