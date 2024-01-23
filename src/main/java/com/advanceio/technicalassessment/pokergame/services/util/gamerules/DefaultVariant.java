package com.advanceio.technicalassessment.pokergame.services.util.gamerules;

public class DefaultVariant implements PokerVariant {

    private static final int DEFAULT_HAND_SIZE = 5; // Default hand size

    @Override
    public int getHandSize() {
        return DEFAULT_HAND_SIZE;
    }


}
