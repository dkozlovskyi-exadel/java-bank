package com.model;

public class Account {
    public int userCash;

    public Account(int initCash) {
        this.userCash = initCash;
    }

    public int infoAboutCash() {
        return userCash;
    }

    public void putCash() {
    }

    public void getCash(int sumToBePaid) {
        this.userCash -= sumToBePaid;
    }
}
