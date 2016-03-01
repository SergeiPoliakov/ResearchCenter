package com.netcracker.unc.mvc.models;

public class TransactionModel {

    private int transactionId;
    private String transactionDate = null;
    private int finObjectId;
    private int cost;
    private int userId;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getFinObjectId() {
        return finObjectId;
    }

    public void setFinObjectId(int finObjectId) {
        this.finObjectId = finObjectId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
