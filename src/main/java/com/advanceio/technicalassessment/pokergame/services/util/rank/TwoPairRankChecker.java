package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.entity.CardRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TwoPairRankChecker implements RankChecker {
    @Override
    public String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        // Implement logic for checking Two Pair
        if (hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        // Group cards by rank
        Map<CardRank, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        // Check if there are two distinct ranks with a count of 2
        long pairCount = rankCounts.values().stream().filter(count -> count == 2).count();

        if (pairCount == 2) {
            return HandRank.TWO_PAIR.name();
        }

        return HandRank.UNKNOWN_HAND.name();
    }
}

