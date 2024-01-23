package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.List;

public class HighCardRankChecker implements RankChecker {
    @Override
    public String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        if (hand == null || hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        // Find the highest-ranked card using streams
        Card highestCard = hand.stream()
                .max((c1, c2) -> Integer.compare(c1.getValue().getValue(), c2.getValue().getValue()))
                .orElse(null);
        return HandRank.HIGH_CARDS.toString() + " " + (highestCard != null ? highestCard.getValue() : "Unknown");
    }
}
