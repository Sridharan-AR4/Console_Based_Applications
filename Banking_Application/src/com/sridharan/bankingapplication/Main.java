package com.sridharan.bankingapplication;

import java.util.List;
import java.util.Scanner;

import com.sridharan.bankingapplication.entity.Transaction;
import com.sridharan.bankingapplication.entity.User;
import com.sridharan.bankingapplication.repository.userRepo;
import com.sridharan.bankingapplication.service.userService;
import java.time.LocalTime;
import java.time.LocalDate;
public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Main main = new Main();
    private static userService userService = new userService();

    public static void main(String[] args) {
        System.out.println("-----Welcome to our banking application-----");
        boolean flag = true;
        while (flag) {
            System.out.println("1.Admin 2.Customer 3.Exit");
            System.out.print("Enter you choice : ");
            int option = sc.nextInt();
            if (option == 1) {
                System.out.print("Enter your password : ");
                String password = sc.next();
                main.initAdmin(password);
            } else if (option == 2) {
                System.out.print("Enter your userName : ");
                String userName = sc.next();
                System.out.print("Enter your password : ");
                String password = sc.next();
                main.initUser(userName, password);
            } else if (option == 3) {
                System.out.println("Thank You for using our application...");
                System.out.println("-------------------------------");
                flag = false;
            } else {
                System.out.println("Please enter a valid choice...");
                System.out.println("-------------------------------");
            }
        }

    }

    private void initUser(String userName, String password) {
        User user = main.login(userName);
        if (user == null) {
            System.out.println("Invalid userName & password...");
        } else if (!user.getPassword().equals(password)) {
            System.out.println("Wrong password...");
        } else {
            System.out.println("Logged in Successfully...");
            boolean flag = true;
            while (flag) {
                System.out.println("1.check bank balance");
                System.out.println("2.Transfer money");
                System.out.println("3.Deposit");
                System.out.println("4.Withdraw money");
                System.out.println("5.See all transactions");
                System.out.println("6.Logout");
                System.out.print("Enter your choice : ");
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        main.checkBalance(user);
                        break;
                    case 2:
                        main.transferMoney(user);
                        break;
                    case 3:
                        main.depositMoney(user);
                        break;
                    case 4:
                        main.withdrawMoney(user);
                        break;
                    case 5:
                        main.getAllUserTransaction(user);
                        break;
                    case 6:
                        flag = false;
                        System.out.println("Logged out Successfully...");
                        System.out.println("-------------------------------");
                    default:
                        break;
                }
            }
        }
    }

    private void initAdmin(String password) {
        boolean flag = true;
        if (password.equals("Sridharan@31")) {
            System.out.println("You are successfully logged in...");
            while (flag) {
                System.out.println("1.Create new customer");
                System.out.println("2.See All Transactions");
                System.out.println("3.Specific User Transactions");
                System.out.println("4.Specific User bank balance");
                System.out.println("5.Close User account");
                System.out.println("6.Logout");
                System.out.print("Enter your choice : ");
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        main.addNewCustomer();
                        break;
                    case 2:
                        main.getAllTransactions();
                        break;
                    case 3:
                        main.getUserTransactions();
                        break;
                    case 4:
                        main.getUserBalance();
                        break;
                    case 5:
                        main.closeUserAccount();
                        break;
                    case 6:
                        flag = false;
                        System.out.println("Logged out Successfully...");
                        System.out.println("-------------------------------");
                        break;
                    default:
                        System.out.println("Enter a valid option...");
                        break;
                }
            }
        } else {
            System.out.println("Login failed wrong password");
        }

    }

    private User login(String userName) {
        return userService.login(userName);
    }
    private void addNewCustomer() {
        System.out.print("Enter userName : ");
        String userName = sc.next();

        System.out.print("Enter password : ");
        String password = sc.next();

        System.out.print("Enter phone number : ");
        String phone = sc.next();

        if (userService.addNewCustomer(userName, password, phone)) {
            System.out.println("Account created successfully...");
        } else {
            System.out.println("Customer account creation failed - userName already taken...");
        }

    }

    private void getAllTransactions(){
        List<Transaction> transactions = userService.getAllTransactions();
        if(transactions.size() == 0){
            System.out.println("No Transaction yet...");
            return;
        }
        System.out.println("-------------------------------");
        for(Transaction tr : transactions){
            System.out.println(tr);
            System.out.println("-------------------------------");
        }
    }
    private void getUserTransactions(){
        System.out.print("Enter the userName : ");
        String userName = sc.next();
        if(!userService.isExistingUser(userName)){
            System.out.println("User Not Found : Please enter a valid UserName..");
            return;
        }
        List<Transaction> transactions = userService.getUserTransactions(userName);
        if(transactions.isEmpty()){
            System.out.println("No Transactions Yet....");
            return;
        }
        for(Transaction tr : transactions){
            System.out.println(tr);
            System.out.println("-------------------------------");
        }
        
    }
    private void getUserBalance(){
        System.out.print("Enter the userName : ");
        String userName = sc.next();
        if(!userService.isExistingUser(userName)){
            System.out.println("User Not Found : Please enter a valid UserName..");
            return;
        }
        System.out.println(userName + " balance is : " + userService.getUserBalance(userName));
    }
    private void closeUserAccount(){
        System.out.print("Enter the userName : ");
        String userName = sc.next();
        if(!userService.isExistingUser(userName)){
            System.out.println("User Not Found : Please enter a valid UserName..");
            return;
        }
        userService.closeUserAccount(userName);
        System.out.println(userName + "'s Account closed Successfully..");
    }
    private void checkBalance(User user) {
        System.out.println("-------------------------------");
        System.out.println("Your Current Balance is : Rs." + userService.checkBalance(user));
        System.out.println("-------------------------------");
    }

    private void depositMoney(User user) {
        System.out.print("Enter the amount you want to deposit : ");
        double amount = sc.nextDouble();
        if (amount < 0) {
            System.out.println("Amount can't be negative...");
        } else {
            userService.depositMoney(user, amount);
            Transaction newTransaction = new Transaction(user.getUserName(), "Deposit", amount, "self", "self", LocalDate.now().toString(), LocalTime.now().toString());
            userService.addUserTransaction(user, newTransaction);
            System.out.println("-------------------------------");
            System.out.println("Rs." + amount + " credited successfully");
            System.out.println("-------------------------------");
        }
    }

    private void withdrawMoney(User user) {
        System.out.print("Enter the amount you want to withdraw : ");
        double amt = sc.nextDouble();
        if(amt > userService.checkBalance(user)){
            System.out.println("Can't withdraw more than your balance...");
        } 
        else if(amt < 0){
            System.out.println("Amount can't be negative...");
        } 
        else{
            Transaction transaction = new Transaction(user.getUserName(), "Withdraw", amt,"self", "self", LocalDate.now().toString(), LocalTime.now().toString());
            userService.addUserTransaction(user, transaction);
            userService.withdrawMoney(user, amt);
            System.out.println("-------------------------------");
            System.out.println("Rs." + amt + " witdrawed successfully...");
            System.out.println("-------------------------------");
        }
    }

    private void transferMoney(User user){
        System.out.print("Enter the customer userName you want to transfer fund : ");
        String userName = sc.next();
        if(!userService.isExistingUser(userName)){
            System.out.println("Not found - Please enter a valid userName");
        }
        else{
            System.out.print("Enter the amount you want to transfer : ");
            double amt = sc.nextDouble();
            if(amt > userService.checkBalance(user)){
                System.out.println("Can't transfer more than your balance...");
            } 
            else if(amt < 0){
                System.out.println("Amount can't be negative...");
            } 
            else{
                Transaction transaction = new Transaction(user.getUserName(), "Fund Transfer", amt, "self", userName, LocalDate.now().toString(), LocalTime.now().toString());
                userService.addUserTransaction(user, transaction);
                Transaction transaction2 = new Transaction(userName, "Fund Transfer", amt, user.getUserName(), "self", LocalDate.now().toString(), LocalTime.now().toString());
                userService.addTransaction(userName, transaction2);
                userService.withdrawMoney(user, amt);
                userService.depositMoney(userName, amt);
                System.out.println("-------------------------------");
                System.out.println("Rs. " + amt + " transferred successfully to " + userName);
                System.out.println("-------------------------------");
            }
        }

    }
    private void getAllUserTransaction(User user){
        List<Transaction> transactions = userService.getAllUserTransaction(user);
        if(transactions.size() == 0){
            System.out.println("No Transaction yet...");
            return;
        }
        System.out.println("-------------------------------");
        for(Transaction tr : transactions){
            System.out.println(tr);
            System.out.println("-------------------------------");
        }
    }
}