package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.entity.CardRank;
import com.advanceio.technicalassessment.pokergame.entity.CardSuit;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.DefaultVariant;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RankCheckerRegressionTests {

    private final PokerVariant variant = new DefaultVariant();

    @Test
    void straightCheckerTreatsAceLowSequenceAsStraight() {
        List<Card> hand = List.of(
                new Card(CardSuit.CLUBS, CardRank.ACE),
                new Card(CardSuit.DIAMONDS, CardRank.TWO),
                new Card(CardSuit.HEARTS, CardRank.THREE),
                new Card(CardSuit.SPADES, CardRank.FOUR),
                new Card(CardSuit.CLUBS, CardRank.FIVE)
        );

        String result = new StraightRankChecker().evaluateHand(hand, variant);

        assertEquals(HandRank.STRAIGHT.name(), result);
    }

    @Test
    void straightCheckerReturnsUnknownWhenHandIsNull() {
        String result = new StraightRankChecker().evaluateHand(null, variant);

        assertEquals(HandRank.UNKNOWN_HAND.name(), result);
    }

    @Test
    void straightFlushCheckerTreatsAceLowSequenceAsStraightFlush() {
        List<Card> hand = List.of(
                new Card(CardSuit.HEARTS, CardRank.ACE),
                new Card(CardSuit.HEARTS, CardRank.TWO),
                new Card(CardSuit.HEARTS, CardRank.THREE),
                new Card(CardSuit.HEARTS, CardRank.FOUR),
                new Card(CardSuit.HEARTS, CardRank.FIVE)
        );

        String result = new StraightFlushRankChecker().evaluateHand(hand, variant);

        assertEquals(HandRank.STRAIGHT_FLUSH.name(), result);
    }

    @Test
    void straightFlushCheckerReturnsUnknownForBrokenStraightEvenWithSameSuit() {
        List<Card> hand = List.of(
                new Card(CardSuit.HEARTS, CardRank.TWO),
                new Card(CardSuit.HEARTS, CardRank.THREE),
                new Card(CardSuit.HEARTS, CardRank.FOUR),
                new Card(CardSuit.HEARTS, CardRank.SIX),
                new Card(CardSuit.HEARTS, CardRank.SEVEN)
        );

        String result = new StraightFlushRankChecker().evaluateHand(hand, variant);

        assertEquals(HandRank.UNKNOWN_HAND.name(), result);
    }

    @Test
    void flushCheckerReturnsEnumCompatibleHandRankName() {
        List<Card> hand = List.of(
                new Card(CardSuit.SPADES, CardRank.TWO),
                new Card(CardSuit.SPADES, CardRank.FIVE),
                new Card(CardSuit.SPADES, CardRank.SEVEN),
                new Card(CardSuit.SPADES, CardRank.NINE),
                new Card(CardSuit.SPADES, CardRank.KING)
        );

        String result = new FlushRankChecker().evaluateHand(hand, variant);

        assertEquals(HandRank.FLUSH.name(), result);
    }

    @Test
    void flushCheckerReturnsUnknownWhenHandIsNull() {
        String result = new FlushRankChecker().evaluateHand(null, variant);

        assertEquals(HandRank.UNKNOWN_HAND.name(), result);
    }

    @Test
    void threeOfAKindReturnsEnumCompatibleHandRankName() {
        List<Card> hand = List.of(
                new Card(CardSuit.SPADES, CardRank.THREE),
                new Card(CardSuit.CLUBS, CardRank.THREE),
                new Card(CardSuit.DIAMONDS, CardRank.THREE),
                new Card(CardSuit.HEARTS, CardRank.NINE),
                new Card(CardSuit.SPADES, CardRank.KING)
        );

        String result = new ThreeOfAKindRankChecker().evaluateHand(hand, variant);

        assertEquals(HandRank.THREE_OF_A_KIND.name(), result);
    }

    @Test
    void fourOfAKindUsesVariantHandSizeAndReturnsUnknownOnSizeMismatch() {
        List<Card> hand = List.of(
                new Card(CardSuit.SPADES, CardRank.THREE),
                new Card(CardSuit.CLUBS, CardRank.THREE),
                new Card(CardSuit.DIAMONDS, CardRank.THREE),
                new Card(CardSuit.HEARTS, CardRank.THREE)
        );

        String result = new FourOfAKindRankChecker().evaluateHand(hand, variant);

        assertEquals(HandRank.UNKNOWN_HAND.name(), result);
    }

    @Test
    void onePairDoesNotMisclassifyThreeOfAKind() {
        List<Card> hand = List.of(
                new Card(CardSuit.SPADES, CardRank.THREE),
                new Card(CardSuit.CLUBS, CardRank.THREE),
                new Card(CardSuit.DIAMONDS, CardRank.THREE),
                new Card(CardSuit.HEARTS, CardRank.NINE),
                new Card(CardSuit.SPADES, CardRank.KING)
        );

        String result = new OnePairRankChecker().evaluateHand(hand, variant);

        assertEquals(HandRank.UNKNOWN_HAND.name(), result);
    }
}
