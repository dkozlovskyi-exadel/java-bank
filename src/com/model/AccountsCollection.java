package com.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountsCollection {
    private final Map<String, Account> activeUsersMap = new HashMap<>();

    public Account getUserAccount(String incomeId) {
        Account currentUserAccount;
        if (activeUsersMap.containsKey(incomeId)) {
            currentUserAccount = activeUsersMap.get(incomeId);
        } else {
            currentUserAccount = addNewUser();
        }
        return currentUserAccount;
    }

    private Account addNewUser() {
        Account newUserAccount;
        System.out.println("Need to sign up! Provide your id!");
        String newUserId = new Scanner(System.in).nextLine();

        newUserAccount = new Account(1000, newUserId);
        activeUsersMap.put(newUserId, newUserAccount);
        return newUserAccount;
    }
}
