package com.model;

import java.util.HashMap;
import java.util.Map;

public class BanknotesCounter {
    // Allowed banknotes to be deposited;
    public final int[] ALLOWED_BANKNOTES = {1, 3, 5, 10, 20, 100};
    // The key is the denomination of the banknote. Value - its quantity in stock;
    public Map<Integer, Integer> banknotesMap = new HashMap<>();
    public int totalSum;

    public BanknotesCounter() {
        totalSum = 0;
        for (int allowedBanknote : ALLOWED_BANKNOTES) {
            banknotesMap.put(allowedBanknote, 0);
        }
    }

    public void incrementBanknoteCounter(int banknote) {
        Integer banknoteAmount = banknotesMap.get(banknote);
        banknotesMap.put(banknote, ++banknoteAmount);
        totalSum += banknote;
    }

    public void decrementBanknoteCounter(int banknote) {
        Integer banknoteAmount = banknotesMap.get(banknote);
        banknotesMap.put(banknote, --banknoteAmount);
        totalSum -= banknote;
    }
}