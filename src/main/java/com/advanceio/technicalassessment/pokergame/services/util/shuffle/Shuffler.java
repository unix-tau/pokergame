package com.advanceio.technicalassessment.pokergame.services.util.shuffle;

import com.advanceio.technicalassessment.pokergame.entity.Card;

import java.util.List;

public interface Shuffler {
    void shuffle(List<Card> deck);

}
