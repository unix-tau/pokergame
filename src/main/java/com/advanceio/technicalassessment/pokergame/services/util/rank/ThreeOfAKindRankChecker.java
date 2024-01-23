package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.entity.CardRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ThreeOfAKindRankChecker implements RankChecker {
    @Override
    public String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        // Implement logic for checking Three of a Kind
        if (hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        // Group cards by rank
        Map<CardRank, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        // Check if there is one rank with a count of 3
        boolean hasThreeOfAKind = rankCounts.values().stream().anyMatch(count -> count == 3);

        if (hasThreeOfAKind) {
            return "Three of a Kind";
        }

        return HandRank.UNKNOWN_HAND.toString();
    }

}
