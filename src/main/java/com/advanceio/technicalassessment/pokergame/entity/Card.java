package com.advanceio.technicalassessment.pokergame.entity;

public final class Card implements Comparable<Card> {

    private final CardSuit suit;
    private final CardRank value;

    public Card(CardSuit suit, CardRank value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public int compareTo(Card other) {
        return this.value.getValue() - other.value.getValue();
    }

    @Override
    public String toString() {
        return value.toString() + suit.toString();
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardRank getValue() {
        return value;
    }

}