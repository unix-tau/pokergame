package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.entity.CardRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.List;
import java.util.stream.Collectors;

public class StraightRankChecker implements RankChecker {
    @Override
    public String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        // Implement logic for checking Straight
        if (hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        // Extract distinct ranks and sort them
        List<CardRank> sortedRanks = hand.stream()
                .map(Card::getValue)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        // Check if the ranks form a consecutive sequence
        if (sortedRanks.size() == 5
                && (sortedRanks.get(4).ordinal() - sortedRanks.get(0).ordinal() == 4)) {
            return HandRank.STRAIGHT.name();
        }

        return HandRank.UNKNOWN_HAND.toString();
    }
}
