package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.entity.CardSuit;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.List;

public class FlushRankChecker implements RankChecker {
    @Override
    public String evaluateHand(List<Card> hand, PokerVariant pokerVariant) {
        // Implement logic for checking Flush
        if (hand.size() != pokerVariant.getHandSize()) {
            return HandRank.UNKNOWN_HAND.toString();
        }

        // Check if all cards have the same suit
        CardSuit firstSuit = hand.get(0).getSuit();
        boolean isFlush = hand.stream().allMatch(card -> card.getSuit().equals(firstSuit));

        if (isFlush) {
            return "Flush";
        }

        return HandRank.UNKNOWN_HAND.toString();
    }
}