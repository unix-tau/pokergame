package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.entity.CardRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.List;
import java.util.stream.Collectors;

public class StraightFlushRankChecker implements RankChecker {
    @Override
    public String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        if (hand == null || hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        List<Card> sortedHand = hand.stream()
                .sorted((c1, c2) -> Integer.compare(c1.getValue().getValue(), c2.getValue().getValue()))
                .collect(Collectors.toList());

        boolean sameSuit = sortedHand.stream().map(Card::getSuit).distinct().count() == 1;

        boolean isStraight = true;
        for (int i = 0; i < sortedHand.size() - 1; i++) {
            if (sortedHand.get(i + 1).getValue().getValue() - sortedHand.get(i).getValue().getValue() != 1) {
                isStraight = false;
                break;
            }
        }

        boolean isWheelStraightFlush = sortedHand.size() == 5
                && sortedHand.get(0).getValue() == CardRank.TWO
                && sortedHand.get(1).getValue() == CardRank.THREE
                && sortedHand.get(2).getValue() == CardRank.FOUR
                && sortedHand.get(3).getValue() == CardRank.FIVE
                && sortedHand.get(4).getValue() == CardRank.ACE;

        if (sameSuit && (isStraight || isWheelStraightFlush)) {
            return HandRank.STRAIGHT_FLUSH.toString();
        }

        return HandRank.UNKNOWN_HAND.toString();
    }
}
