package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HandQueryTest {

    @Test
    public void handWithValueOf22IsBusted() throws Exception {
        List<Card> cards = CardFactory.createCardsOfRank("9", "8", "5");
        Hand hand = Hand.createTestHand(cards);

        assertThat(hand.isBusted())
                .isTrue();
    }

}
