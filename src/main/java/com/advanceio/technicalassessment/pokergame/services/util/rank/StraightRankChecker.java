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
        if (hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        List<CardRank> sortedRanks = hand.stream()
                .map(Card::getValue)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        if (sortedRanks.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        boolean isStandardStraight = sortedRanks.get(sortedRanks.size() - 1).ordinal()
                - sortedRanks.get(0).ordinal() == pokerVariant.getHandSize() - 1;

        boolean isWheelStraight = sortedRanks.size() == 5
                && sortedRanks.get(0) == CardRank.TWO
                && sortedRanks.get(1) == CardRank.THREE
                && sortedRanks.get(2) == CardRank.FOUR
                && sortedRanks.get(3) == CardRank.FIVE
                && sortedRanks.get(4) == CardRank.ACE;

        if (isStandardStraight || isWheelStraight) {
            return HandRank.STRAIGHT.name();
        }

        return HandRank.UNKNOWN_HAND.toString();
    }
}
