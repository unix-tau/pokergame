package com.advanceio.technicalassessment.pokergame.services.util.rank;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;

import java.util.List;

public interface RankChecker {

    public  String evaluateHand(List<Card> hand, PokerVariant pokerVariant);
}
