package com.service;

import com.model.CashMachine;

public class Main {

    public static void main(String[] args) {
        CashMachine cashMachine = new CashMachine();

        cashMachine.give(10);
        //TODO: Стратегия 2 = купюры всех номиналов расходовались максимально равномерно
    }
}
