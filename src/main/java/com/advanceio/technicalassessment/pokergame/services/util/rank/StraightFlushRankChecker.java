package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.List;

public class StraightFlushRankChecker implements RankChecker {
    @Override
    public String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        if (hand == null || pokerVariant == null || hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        if (hand.stream().anyMatch(card -> card == null || card.getSuit() == null || card.getValue() == null)) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        boolean sameSuit = hand.stream().map(Card::getSuit).distinct().count() == 1;
        if (!sameSuit) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        String straightResult = new StraightRankChecker().evaluateHand(hand, pokerVariant);

        if (HandRank.STRAIGHT.name().equals(straightResult)) {
            return HandRank.STRAIGHT_FLUSH.toString();
        }

        return HandRank.UNKNOWN_HAND.toString();
    }
}
