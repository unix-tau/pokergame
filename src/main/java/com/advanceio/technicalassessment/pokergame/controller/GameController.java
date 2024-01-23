package com.advanceio.technicalassessment.pokergame.controller;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.services.PokerGameService;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;
import com.advanceio.technicalassessment.pokergame.services.util.rank.RankChecker;

import java.util.List;

public class GameController {


    private PokerGameService pokerGame = null;

    //game initialization
    public GameController() {
        this.pokerGame = new PokerGameService();
    }

    public void shuffleDeck() {
        pokerGame.shuffleDeck();
    }

    public void dealHand(PokerVariant pokerVariant) {
        pokerGame.dealHand(pokerVariant);
    }

    public String evaluateHand(PokerVariant pokerVariant, RankChecker rankChecker) {
        String handValue = pokerGame.evaluateHand(pokerVariant, rankChecker);
        return handValue;
    }

    public PokerGameService getPokerGame() {
        return pokerGame;
    }


    public List<Card> getPlayerHand() {
        List<Card> cards = pokerGame.getPlayerHand();
        return cards;
    }

    public List<Card> getDeck() {
        return pokerGame.getDeck();
    }

    public List<Card> getOriginalDeck() {
        return pokerGame.getOriginalDeck();
    }

    public void setOriginalDeck(List<Card> originalDeck) {
        pokerGame.setOriginalDeck(originalDeck);
    }
}

