package com.jitterted.ebp.blackjack;

public class Wallet {
    private int balance = 0;

    public boolean isEmpty() {
        return balance == 0;
    }

    public void addMoney(int amount) {
        requireNonNegative(amount);
        balance += amount;
    }

    private void requireNonNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
    }

    public int balance() {
        return balance;
    }
}
