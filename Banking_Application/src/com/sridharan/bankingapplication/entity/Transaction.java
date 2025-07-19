package com.sridharan.bankingapplication.entity;

public class Transaction {
    private String userName;
    private String typeOfTransaction;
    private double amount;
    private String from;
    private String to;
    private String date;
    private String time;
    public Transaction(String userName, String typeOfTransaction,double amount, String from, String to, String date, String time) {
        this.userName = userName;
        this.typeOfTransaction = typeOfTransaction;
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
    }
    @Override
    public String toString() {
        return "Transaction [userName = " + userName + ", typeOfTransaction = " + typeOfTransaction + ", amount = " + amount
                + ", from = " + from + ", to = " + to + ", date = " + date + ", time = " + time + "]";
    }
    

}
