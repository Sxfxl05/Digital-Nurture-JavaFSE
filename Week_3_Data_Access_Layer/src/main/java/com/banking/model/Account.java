package com.banking.model;

public class Account {
    private int accountId;
    private String customerName;
    private double balance;

    public Account(int accountId, String customerName, double balance) {
        this.accountId = accountId;
        this.customerName = customerName;
        this.balance = balance;
    }

    // Getters and Setters
    public int getAccountId() { return accountId; }
    public String getCustomerName() { return customerName; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}