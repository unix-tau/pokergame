package com.advanceio.technicalassessment.pokergame;

import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.entity.CardRank;
import com.advanceio.technicalassessment.pokergame.entity.CardSuit;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PokergameApplicationTests {


    private static List<Card> generateThreeOfAKindHand() {
        // Generating a sample Three of a Kind hand for testing
        List<Card> threeOfAKindHand = new ArrayList<>();
        threeOfAKindHand.add(new Card(CardSuit.HEARTS, CardRank.TWO));
        threeOfAKindHand.add(new Card(CardSuit.DIAMONDS, CardRank.TWO));
        threeOfAKindHand.add(new Card(CardSuit.CLUBS, CardRank.TWO));
        threeOfAKindHand.add(new Card(CardSuit.HEARTS, CardRank.SEVEN));
        threeOfAKindHand.add(new Card(CardSuit.SPADES, CardRank.KING));
        return threeOfAKindHand;
    }

    @Test
    void generateThreeOfAKindHand() {
        // Example usage with ThreeOfAKindRankChecker
        List<Card> threeOfAKindHand = generateThreeOfAKindHand();
        String threeOfAKindHandRank = evaluateHand(threeOfAKindHand, new ThreeOfAKindRankChecker());
        System.out.println("Hand: " + threeOfAKindHand);
        System.out.println("Hand Rank: " + threeOfAKindHandRank);

    }

}
