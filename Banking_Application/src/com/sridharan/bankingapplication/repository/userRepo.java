package com.sridharan.bankingapplication.repository;

import com.sridharan.bankingapplication.entity.Transaction;
import com.sridharan.bankingapplication.entity.User;
import java.util.*;
public class userRepo {
    private static Map<String, User> users = new HashMap<>();
    private static List<Transaction> allTransactions = new ArrayList<>();
    public boolean addNewCustomer(String userName, String password, String contactNo){
        User newUser = new User(userName, password, contactNo);
        return users.put(userName, newUser) == null ? true : false;
    }

    public User login(String userName){
        return users.get(userName);
    }
    public double checkBalance(User user){
        return user.getBalance();
    }
    public double getUserBalance(String userName){
        return users.get(userName).getBalance();
    }
    public boolean isExistingUser(String userName){
        return users.containsKey(userName);
    }
    public void depositMoney(User user, double amt){
        user.setBalance(user.getBalance() + amt);
    }
    public void withdrawMoney(User user, double amt){
        user.setBalance(user.getBalance() - amt);
    }
    public void depositMoney(String userName, double amt) {
        User user2 = users.get(userName);
        user2.setBalance(user2.getBalance() + amt);
    }

    public List<Transaction> getAllTransactions(){
        return allTransactions;
    }
    public List<Transaction> getUserTransactions(String userName){
        return users.get(userName).getTransactions();
    }
    public List<Transaction> getAllUserTransaction(User user){
        return user.getTransactions();
    }
    public void addUserTransaction(User user, Transaction transaction){
        user.setTransactions(transaction);
        allTransactions.add(transaction);
    }
    public void addTransaction(String userName, Transaction transaction){
        users.get(userName).setTransactions(transaction);
    }

    public void closeUserAccount(String userName){
        users.remove(userName);
    }
}
