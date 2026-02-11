package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.Comparator;
import java.util.List;

public class HighCardRankChecker implements RankChecker {
    @Override
    public String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        if (hand == null || pokerVariant == null || hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        if (hand.stream().anyMatch(card -> card == null || card.getValue() == null)) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        Card highestCard = hand.stream()
                .max(Comparator.comparingInt(card -> card.getValue().getValue()))
                .orElse(null);

        return HandRank.HIGH_CARDS + " " + (highestCard != null ? highestCard.getValue() : "Unknown");
    }
}
