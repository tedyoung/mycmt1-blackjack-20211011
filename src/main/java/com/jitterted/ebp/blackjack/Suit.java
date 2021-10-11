package com.jitterted.ebp.blackjack;

import java.util.List;

public enum Suit {
    HEARTS("♥"),
    CLUBS("♣"),
    DIAMONDS("♦"),
    SPADES("♠");

    static final List<String> SUITS = List.of("♠", "♦", "♥", "♣");
    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    // TEMPORARY
    // SCAFFOLDING - will go away
    @Deprecated
    public static Suit from(String symbol) {
        for (Suit suit : values()) {
            if (suit.symbol().equals(symbol)) {
                return suit;
            }
        }
        return null;
    }

    public String symbol() {
        return symbol;
    }
}
