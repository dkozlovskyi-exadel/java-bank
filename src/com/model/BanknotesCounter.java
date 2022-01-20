package com.model;

import java.util.HashMap;
import java.util.Map;

public class BanknotesCounter {
    // Allowed banknotes to be deposited;
    public final int[] ALLOWED_BANKNOTES = {1, 3, 5, 10, 20, 100};
    // The key is the denomination of the banknote. Value - its quantity in stock;
    public Map<Integer, Integer> banknotesCounter = new HashMap<>();
    public int totalSum;

    public BanknotesCounter() {
        totalSum = 0;
        for (int allowedBanknote : ALLOWED_BANKNOTES) {
            banknotesCounter.put(allowedBanknote, 0);
        }
    }

    public void incrementBanknoteCounter(int banknote) {
        Integer banknoteAmount = banknotesCounter.get(banknote);
        banknotesCounter.put(banknote, ++banknoteAmount);
        totalSum += banknote;
    }

    public void decrementBanknoteCounter(int banknote) {
        Integer banknoteAmount = banknotesCounter.get(banknote);
        banknotesCounter.put(banknote, --banknoteAmount);
        totalSum -= banknote;
    }

    public int useBiggestBanknotesFirst(int leftToPay) {
        int biggestBanknote = 0;

        for (Map.Entry<Integer, Integer> item : banknotesCounter.entrySet()) {

            int banknoteAmount = item.getValue();
            int banknoteNominal = item.getKey();

            if (banknoteAmount > 0 && banknoteNominal > biggestBanknote) {
                biggestBanknote = banknoteNominal;
            }
        }
        // If you need to add a smaller denomination, to pay out the desired amount.
        if (biggestBanknote > leftToPay) {
            return findNearestBanknote(leftToPay);
        }

        return biggestBanknote;
    }

    /**
     * Use recursion. If there is no such currency in {@link #banknotesCounter} or the currency is not available -
     * add + 1 and repeat the loop;
     *
     * @param leftToPay - How much more is needed to pay the full amount;
     * @return The desired denomination of the banknote or the closest one in a big way;
     */
    private int findNearestBanknote(int leftToPay) {
        int banknoteAmount;
        int banknoteNominal = leftToPay;

        if (banknotesCounter.containsKey(banknoteNominal)) {
            banknoteAmount = banknotesCounter.get(banknoteNominal);

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