package com.curso.baptisterio.java;

public class TransactionRecord {
    private User user;
    private LocalDateWrapper date;
    private int amount;

    public TransactionRecord(User user, LocalDateWrapper date, int amount) {
        this.user   = user;
        this.date   = date;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public LocalDateWrapper getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }
}
