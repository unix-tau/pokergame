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
    public String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        if (hand == null || pokerVariant == null || hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        if (hand.stream().anyMatch(card -> card == null || card.getValue() == null)) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        Map<CardRank, Long> rankCounts = hand.stream()
                .collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));

        long pairs = rankCounts.values().stream().filter(count -> count == 2).count();
        boolean hasTripsOrQuads = rankCounts.values().stream().anyMatch(count -> count >= 3);

        if (pairs == 1 && !hasTripsOrQuads) {
            return HandRank.ONE_PAIR.name();
        }

        return HandRank.UNKNOWN_HAND.toString();
    }
}
