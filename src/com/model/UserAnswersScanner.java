package com.model;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class UserAnswersScanner {
    /**
     * @param availableSumToBePaid - How much is in {@link BanknotesCounter} or in {@link Account}
     * @return boolean - User answer;
     */
    public boolean ifNotEnoughMoneyToBePaid(int availableSumToBePaid, String whereNotEnoughMoney) {
        //The Cash Machine tells you differently if
        String notEnoughMoneyMessage;

        if (Objects.equals(whereNotEnoughMoney, "Cash Machine")) {
            notEnoughMoneyMessage = "There are not enough funds in the account";
        } else {
            notEnoughMoneyMessage = "Cash Machine runs out of banknotes";
        }
        
        System.out.println(notEnoughMoneyMessage);
        System.out.println("Available sum to pay is: " + availableSumToBePaid + ". Pay it?");
        Scanner scan = new Scanner(System.in);
        String answer = scan.nextLine().toUpperCase(Locale.ROOT);

        if (answer.equals("Y")) {
            System.out.println("You have confirmed payment of " + availableSumToBePaid + " grn.");
            return true;
        } else {
            System.out.println("Operation rejected.");
            return false;
        }
    }
}
