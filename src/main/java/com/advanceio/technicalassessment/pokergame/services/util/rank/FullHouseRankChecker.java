package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.entity.CardRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FullHouseRankChecker implements RankChecker {
    @Override
    public String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        // Implement logic for checking Full House
        if (hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        // Group cards by rank
        Map<CardRank, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        // Check if there is one rank with a count of 3 and another rank with a count of 2
        boolean hasThreeOfAKind = rankCounts.values().stream().anyMatch(count -> count == 3);
        boolean hasOnePair = rankCounts.values().stream().anyMatch(count -> count == 2);

        if (hasThreeOfAKind && hasOnePair) {
            return HandRank.FULL_HOUSE.toString();
        }

        return HandRank.UNKNOWN_HAND.toString();
    }
}

