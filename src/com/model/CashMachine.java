package com.model;

public class CashMachine {
    // Class with banknotes amount and methods to manipulate with them;
    BanknotesCounter banknotesCounter = new BanknotesCounter();
    private final int[] ALLOWED_BANKNOTES = banknotesCounter.ALLOWED_BANKNOTES;

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
}

