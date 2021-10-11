package com.jitterted.ebp.blackjack;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {
    private static final Suit DUMMY_SUIT = Suit.HEARTS;

    static Card withRankOf(String s) {
        return new Card(DUMMY_SUIT, s);
    }

    static List<Card> createCardsOfRank(String... ranks) {
        List<Card> cards = new ArrayList<>();
        for (String rank : ranks) {
            cards.add(withRankOf(rank));
        }
        return cards;
    }
}
