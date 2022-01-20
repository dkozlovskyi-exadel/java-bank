package com.model;

import java.util.Map;

public class CashMachine {
    // Class with user QNA;
    UserAnswersScanner userAnswersScanner = new UserAnswersScanner();
    // Class with banknotes amount and methods to manipulate with them;
    BanknotesCounter banknotesCounter = new BanknotesCounter();
    // Banknotes collection;
    Map<Integer, Integer> counterMap = banknotesCounter.banknotesCounter;
    private final int[] ALLOWED_BANKNOTES = banknotesCounter.ALLOWED_BANKNOTES;


    // Info about availability of banknotes at CashMachine;
    public void cache() {
        for (Map.Entry<Integer, Integer> item : counterMap.entrySet()) {

            int banknoteAmount = item.getValue();
            int banknoteNominal = item.getKey();

            System.out.println("Nominal: " + banknoteNominal + ", Amount " + banknoteAmount);
        }
    }

    /**
     * Check if out CashMachine supports the introduction of banknotes from {@link #ALLOWED_BANKNOTES};
     * Inform user about operation outputting text to the console;
     *
     * @param banknote - Banknote nominal;
     */
    public void put(int banknote) {
        String resultText = "Банкнота не принята. Попробуйте еще";

        for (int allowedBanknote : ALLOWED_BANKNOTES) {
            if (banknote == allowedBanknote) {
                resultText = "Банкнота добавлена";
                banknotesCounter.incrementBanknoteCounter(banknote);
                break;
            }
        }
        System.out.println(resultText);
    }

    /**
     * If there is no required amount, we request a scenario for continuing the operation through {@link #userAnswersScanner};
     *
     * @param sumToBePaid - The amount the customer needs to receive;
     */
    public void give(int sumToBePaid) {
        int totalSumInMachine = banknotesCounter.totalSum;

        //If we do not have the required amount in stock;
        if (totalSumInMachine < sumToBePaid) {
            boolean userAnswer = userAnswersScanner.ifNotEnoughMoneyInMachine(totalSumInMachine);
            if (!userAnswer) {
                return;
            }
            // Agreed - pays everything we have;
            useBiggestBanknoteFistOperation(totalSumInMachine);
        } else {
            useBiggestBanknoteFistOperation(sumToBePaid);
        }
    }

    /**
     * Implemented the first strategy;
     * To keep the smallest bills in the cash register for as long as possible;
     *
     * @param sumToBePaid - the amount specified by the user;
     */
    private void useBiggestBanknoteFistOperation(int sumToBePaid) {
        int banknote;
        for (int leftToPay = sumToBePaid; leftToPay > 0; leftToPay -= banknote) {
            banknote = banknotesCounter.useBiggestBanknotesFirst(leftToPay);
            banknotesCounter.decrementBanknoteCounter(banknote);
        }
    }
}

