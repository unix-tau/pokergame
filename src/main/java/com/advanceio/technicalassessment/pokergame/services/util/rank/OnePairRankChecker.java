package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.entity.CardRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OnePairRankChecker implements RankChecker {
    @Override
    public   String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        if (hand == null || hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        // Group cards by rank
        Map<CardRank, List<Card>> groupedByRank = hand.stream()
                .collect(Collectors.groupingBy(Card::getValue));

        // Filter for pairs
        List<CardRank> pairs = groupedByRank.entrySet().stream()
                .filter(entry -> entry.getValue().size() == 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (pairs.size() == 1) {
            return HandRank.ONE_PAIR.toString();

        } else {
            return HandRank.UNKNOWN_HAND.toString();
        }
    }
}
