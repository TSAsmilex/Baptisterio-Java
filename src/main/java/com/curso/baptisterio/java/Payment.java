package com.curso.baptisterio.java;
import java.util.ArrayList;

public class Payment {
    private ArrayList<TransactionRecord> transactions = new ArrayList<>();

    public boolean register (User user, LocalDateWrapper date, ArrayList<CoinEuro> coins) {
        if (coins.size() == 0) {
            return false;
        }

        var total = CoinEuro.sum(coins);

        if (total != Baptisterio.PRICE) {
            return false;
        }

        transactions.add(
            new TransactionRecord(user, date, total)
        );

        return true;
    }
}