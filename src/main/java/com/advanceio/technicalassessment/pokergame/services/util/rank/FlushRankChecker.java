package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.entity.CardSuit;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.List;

public class FlushRankChecker implements RankChecker {
    @Override
    public String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        if (hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        CardSuit firstSuit = hand.get(0).getSuit();
        boolean isFlush = hand.stream().allMatch(card -> card.getSuit().equals(firstSuit));

        if (isFlush) {
            return HandRank.FLUSH.name();
        }

        return HandRank.UNKNOWN_HAND.toString();
    }
}
