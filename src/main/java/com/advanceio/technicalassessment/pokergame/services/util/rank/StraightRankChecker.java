package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.entity.CardRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.Comparator;
import java.util.List;

public class StraightRankChecker implements RankChecker {
    @Override
    public String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        if (hand == null || pokerVariant == null || hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        if (hand.stream().anyMatch(card -> card == null || card.getValue() == null)) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        List<CardRank> sortedRanks = hand.stream()
                .map(Card::getValue)
                .distinct()
                .sorted(Comparator.comparingInt(CardRank::getValue))
                .toList();

        if (sortedRanks.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        boolean isStandardStraight = true;
        for (int i = 0; i < sortedRanks.size() - 1; i++) {
            if (sortedRanks.get(i + 1).getValue() - sortedRanks.get(i).getValue() != 1) {
                isStandardStraight = false;
                break;
            }
        }

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
