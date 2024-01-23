package com.advanceio.technicalassessment.pokergame.services.util.shuffle;

import com.advanceio.technicalassessment.pokergame.entity.Card;

import java.util.List;
import java.util.Random;

public class DefaultShufflerStrategyimplements implements Shuffler {
    @Override
    public void shuffle(List<Card> deck) {
        Random random = new Random();

        for (int i = deck.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swap cards at positions i and j
            Card temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }
}
