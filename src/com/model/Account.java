package com.model;

public class Account {
    public int userCash;
    public String userId;

    public Account(int initCash, String userId) {
        this.userCash = initCash;
        this.userId = userId;
    }

    public int getCashInfo() {
        return userCash;
    }

    public void putCash() {
    }

    public void getCash(int sumToBePaid) {
        this.userCash -= sumToBePaid;
    }
}
