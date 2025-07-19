package com.sridharan.bankingapplication.service;

import java.util.List;

import com.sridharan.bankingapplication.entity.Transaction;
import com.sridharan.bankingapplication.entity.User;
import com.sridharan.bankingapplication.repository.userRepo;

public class userService {
    private static userRepo userRepo = new userRepo();

    public boolean addNewCustomer(String userName, String password, String contactNo){
        return userRepo.addNewCustomer(userName, password, contactNo);
    }
    public User login(String userName){
        return userRepo.login(userName);
    }
    public boolean isExistingUser(String userName){
        return userRepo.isExistingUser(userName);
    }
    public double checkBalance(User user){
        return userRepo.checkBalance(user);
    }
    public void depositMoney(User user, double amt){
        userRepo.depositMoney(user, amt);
    }
    public void withdrawMoney(User user, double amt){
        userRepo.withdrawMoney(user, amt);
    }
    public void depositMoney(String userName, double amt) {
        userRepo.depositMoney(userName, amt);
    }

    public void addUserTransaction(User user, Transaction transaction){
        userRepo.addUserTransaction(user, transaction);
    }

    public void addTransaction(String userName, Transaction transaction){
        userRepo.addTransaction(userName, transaction);
    }
    public List<Transaction> getAllTransactions(){
        return userRepo.getAllTransactions();
    }
    public List<Transaction> getAllUserTransaction(User user){
        return userRepo.getAllUserTransaction(user);
    }
    public List<Transaction> getUserTransactions(String userName){
        return userRepo.getUserTransactions(userName);
    }

    public double getUserBalance(String userName){
        return userRepo.getUserBalance(userName);
    }

    public void closeUserAccount(String userName){
        userRepo.closeUserAccount(userName);
    }
}
