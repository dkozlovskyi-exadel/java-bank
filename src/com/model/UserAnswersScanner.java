package com.model;

import java.util.Locale;
import java.util.Scanner;

public class UserAnswersScanner {
    /**
     * @param totalSumInMachine - How much is in {@link BanknotesCounter}
     * @return boolean - User answer;
     */
    public boolean ifNotEnoughMoneyToBePaid(int totalSumInMachine) {
        System.out.println("Available sum to pay is: " + totalSumInMachine + ". Pay it?");
        Scanner scan = new Scanner(System.in);
        String answer = scan.nextLine().toUpperCase(Locale.ROOT);

        if (answer.equals("Y")) {
            System.out.println("You have confirmed payment of " + totalSumInMachine + " grn.");
            return true;
        } else {
            System.out.println("Operation rejected.");
            return false;
        }
    }
}
