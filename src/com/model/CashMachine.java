package com.model;

import java.util.Map;

public class CashMachine {
    // Class with user QNA;
    UserAnswersScanner userAnswersScanner = new UserAnswersScanner();
    // Class with banknotes amount and methods to manipulate with them;
    BanknotesCounter banknotesCounter = new BanknotesCounter();
    // Banknotes collection;
    Map<Integer, Integer> banknotesMap = banknotesCounter.banknotesMap;
    private final byte[] ALLOWED_BANKNOTES = banknotesCounter.ALLOWED_BANKNOTES;
    BanknotesHistory banknotesHistory = new BanknotesHistory(ALLOWED_BANKNOTES);

    // Info about availability of banknotes at CashMachine;
    public void cache() {
        StringBuilder cacheResult = new StringBuilder();
        for (Map.Entry<Integer, Integer> item : banknotesMap.entrySet()) {

            int banknoteAmount = item.getValue();
            int banknoteNominal = item.getKey();
            cacheResult.append("Nominal: ").append(banknoteNominal).append(", Amount ").append(banknoteAmount).append("\n");
        }
        System.out.println(cacheResult + "Money: " + banknotesCounter.totalSum);
    }

    public void stat() {
        Map<Integer, BanknotesHistory.BanknoteHistoryData> historyMap = banknotesHistory.getHistoryMap();
        for (Map.Entry<Integer, BanknotesHistory.BanknoteHistoryData> banknote : historyMap.entrySet()) {

            int banknoteNominal = banknote.getKey();
            BanknotesHistory.BanknoteHistoryData banknoteData = banknote.getValue();

            System.out.println("Nominal: " + banknoteNominal +
                    ", Received: " + banknoteData.banknotesReceived +
                    ", PaidOut: " + banknoteData.banknotesPaidOut +
                    ", Available: " + banknoteData.availableQuantity);
        }
    }

    /**
     * Check if out CashMachine supports the introduction of banknotes from {@link #ALLOWED_BANKNOTES};
     * Inform user about operation outputting text to the console;
     *
     * @param banknote - Banknote nominal;
     */
    public void put(int banknote) {
        String resultText = "Banknote not accepted. Try again";

        for (int allowedBanknote : ALLOWED_BANKNOTES) {
            if (banknote == allowedBanknote) {
                resultText = "Banknote added";
                banknotesCounter.incrementBanknoteCounter(banknote);
                banknotesHistory.banknoteReceiptWriteInHistory(banknote);
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
        int paymentSum;

        //If we do not have the required amount in stock;
        if (totalSumInMachine < sumToBePaid) {
            boolean userAnswer = userAnswersScanner.ifNotEnoughMoneyInMachine(totalSumInMachine);
            if (!userAnswer) {
                return;
            }
            // Agreed - pays everything we have;
//            useBiggestBanknoteFistOperation(totalSumInMachine);
            useBanknotesEvenlyStrategy(totalSumInMachine);
        } else {
//            useBiggestBanknoteFistOperation(sumToBePaid);
            useBanknotesEvenlyStrategy(sumToBePaid);
        }
    }

    /**
     * Method will be in class Strategy in homework_2
     * Implemented the first strategy;
     * To keep the smallest bills in the cash register for as long as possible;
     *
     * @param sumToBePaid - the amount specified by the user;
     */
    private void useBiggestBanknoteFistOperation(int sumToBePaid) {
        int banknoteForPay = 0;

        for (int leftToPay = sumToBePaid; leftToPay > 0; leftToPay -= banknoteForPay) {

            for (Map.Entry<Integer, Integer> item : banknotesMap.entrySet()) {

                int banknoteAmount = item.getValue();
                int banknoteNominal = item.getKey();

                if (banknoteAmount > 0 && (banknoteNominal > banknoteForPay || banknoteNominal == leftToPay)) {
                    banknoteForPay = banknoteNominal;

                    if (banknoteForPay == leftToPay) {
                        break;
                    }
                }
            }

            // If you need to add a smaller denomination, to pay out the desired amount.
            if (banknoteForPay > leftToPay) {
                banknoteForPay = findNearestBanknote(leftToPay);
            }

            banknotesCounter.decrementBanknoteCounter(banknoteForPay);
            banknotesHistory.banknotePayoutWriteInHistory(banknoteForPay);
        }
    }

    /**
     * Method will be in class Strategy in homework_2
     * Implemented the second strategy;
     *
     * @param sumToBePaid - Get amount of needed money from user
     */
    private void useBanknotesEvenlyStrategy(int sumToBePaid) {
        int banknoteForPay = 0;
        int leftToPay;

        for (leftToPay = sumToBePaid; leftToPay > 0; leftToPay -= banknoteForPay) {
            int biggestAmount = 0;

            for (Map.Entry<Integer, Integer> item : banknotesMap.entrySet()) {

                int banknoteAmount = item.getValue();
                if (banknoteAmount > 0 && banknoteAmount > biggestAmount) {
                    biggestAmount = banknoteAmount;
                    banknoteForPay = item.getKey();
                }
            }

            // If you need to add a smaller denomination, to pay out the desired amount.
            if (banknoteForPay > leftToPay) {
                banknoteForPay = findNearestBanknote(leftToPay);
            }

            banknotesCounter.decrementBanknoteCounter(banknoteForPay);
            banknotesHistory.banknotePayoutWriteInHistory(banknoteForPay);
        }
    }

    /**
     * If there is no such currency in {@link #banknotesMap} or the currency is not available -
     * add + 1 and repeat the loop;
     *
     * @param leftToPay - How much more is needed to pay the full amount;
     * @return The desired denomination of the banknote or the closest one in a big way;
     */
    private int findNearestBanknote(int leftToPay) {
        int banknoteAmount;
        int banknoteNominal = leftToPay;
        if (banknotesMap.containsKey(banknoteNominal)) {
            banknoteAmount = banknotesMap.get(banknoteNominal);

            if (banknoteAmount > 0) {
                return banknoteNominal;
            } else {
                return findNearestBanknote(++banknoteNominal);
            }
        } else {
            return findNearestBanknote(++banknoteNominal);
        }
    }
}

