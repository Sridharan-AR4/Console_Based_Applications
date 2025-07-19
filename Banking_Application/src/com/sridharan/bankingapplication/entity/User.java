package com.sridharan.bankingapplication.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String password;
    private String contactNumber;
    private double balance;
    private List<Transaction> transactions;
    public User(String userName, String password,String contactNumber) {
        this.userName = userName;
        this.password = password;
        this.contactNumber = contactNumber;
        this.balance = 500;
        transactions = new ArrayList<>();
    }
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction transaction) {
        this.transactions.add(transaction);
    }
  
}
