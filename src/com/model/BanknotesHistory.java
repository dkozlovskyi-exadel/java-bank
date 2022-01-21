package com.model;

import java.util.HashMap;
import java.util.Map;

public class BanknotesHistory {
    public Map<Integer, BanknoteHistoryData> banknotesHistoryMap = new HashMap<>();

    // Init historyMap
    // key - banknoteNominal
    // value - BanknoteHistoryData class
    BanknotesHistory(byte[] allowedBanknotes) {
        for (int banknoteNominal : allowedBanknotes) {
            banknotesHistoryMap.put(banknoteNominal, new BanknoteHistoryData());
        }
    }

    public Map<Integer, BanknoteHistoryData> getHistoryMap() {
        return banknotesHistoryMap;
    }

    //Receipt
    public void banknoteReceiptWriteInHistory(int banknote) {
        BanknoteHistoryData banknoteClass = banknotesHistoryMap.get(banknote);
        banknoteClass.setBanknotesReceived();
    }

    //Payout
    public void banknotePayoutWriteInHistory(int banknote) {
        BanknoteHistoryData banknoteClass = banknotesHistoryMap.get(banknote);
        banknoteClass.setBanknotesPaidOut();
    }

    public static class BanknoteHistoryData {
        public int banknotesReceived = 0;
        public int banknotesPaidOut = 0;
        public int availableQuantity = 0;

        public void setBanknotesReceived() {
            banknotesReceived += 1;
            availableQuantity = banknotesReceived - banknotesPaidOut;
        }

        public void setBanknotesPaidOut() {
            banknotesPaidOut += 1;
            availableQuantity = banknotesReceived - banknotesPaidOut;
        }
    }
}
