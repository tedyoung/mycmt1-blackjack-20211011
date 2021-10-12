package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HandValueAceTest {

    @Test
    public void handWithOneAceTwoCardsIsValuedAt11() throws Exception {
        List<Card> cards = CardFactory.createCardsOfRank("A", "5");
        Hand hand = Hand.createTestHand(cards);

        assertThat(hand.value())
                .isEqualTo(11 + 5);
    }

    @Test
    public void handWithOneAceAndOtherCardsEqualTo11IsValuedAt1() throws Exception {
        List<Card> cards = CardFactory.createCardsOfRank("A", "8", "3");
        Hand hand = Hand.createTestHand(cards);

        assertThat(hand.value())
                .isEqualTo(1 + 8 + 3);
    }

}
