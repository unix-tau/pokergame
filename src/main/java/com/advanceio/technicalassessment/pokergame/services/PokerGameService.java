package com.advanceio.technicalassessment.pokergame.services;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.entity.CardRank;
import com.advanceio.technicalassessment.pokergame.entity.CardSuit;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;
import com.advanceio.technicalassessment.pokergame.services.util.rank.OnePairRankChecker;
import com.advanceio.technicalassessment.pokergame.services.util.rank.RankChecker;
import com.advanceio.technicalassessment.pokergame.services.util.shuffle.Shuffler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerGameService {
    private List<Card> deck;
    private List<Card> playerHand;
    private Shuffler shuffler;
    private RankChecker rankChecker;
    private int handSize;

    public PokerGameService() {
        deck = initializeDeck();
        playerHand = new ArrayList<>();
    }

    private List<Card> initializeDeck() {
        List<Card> newDeck = new ArrayList<>();
        CardRank[] cardRanks = CardRank.values();
        CardSuit[] cardSuits = CardSuit.values();

        for (CardRank cardRank : cardRanks) {
            for (CardSuit cardSuit : cardSuits) {
                newDeck.add(new Card(cardSuit, cardRank));
            }
        }

        return newDeck;
    }

    public void shuffleDeck() {
        System.out.println("\nshuffling... shuffling... shuffling...  ");
        Collections.shuffle(deck);
    }

    public void dealHand(PokerVariant pokerVariant) {
        playerHand = deck.subList(0, pokerVariant.getHandSize());
        deck = deck.subList(5, deck.size());
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public String evaluateHand(PokerVariant pokerVariant,  RankChecker  rankChecker) {
        List<Card> playerhand = getPlayerHand();
        String handRank = rankChecker.evaluateHand(playerhand, pokerVariant);
        return handRank;
    }


}

