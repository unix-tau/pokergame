package com.advanceio.technicalassessment.pokergame;

import com.advanceio.technicalassessment.pokergame.controller.GameController;
import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.DefaultVariant;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.HandRank;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;
import com.advanceio.technicalassessment.pokergame.services.util.rank.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

//@SpringBootApplication
public class PokergameApplication {


    static int DEFAULT_INDEX = 4;

    //tests
    public static void main(String[] args) {
        //SpringApplication.run(PokergameApplication.class);
        //in future  to available over the web
        Scanner scanner = new Scanner(System.in);

        //choosing poker variant of game size of 5 cards
        PokerVariant pokerVariant = new DefaultVariant();

        //accessing the back-end-logic
        GameController gameController = new GameController();

        //keep original Deck cards
        List<Card> deckOg = gameController.getOriginalDeck();

        System.out.println("\nWelcome to the Five-Card Draw Poker Game!");

        System.out.println("\n ***********HERE ARE TESTCASES RUNNABLES PLEASE CHOOSE INDEX STARTING FROM ZERO FOR THE fIRST ONE*************");
        Stream.of(HandRank.values()).forEach(System.out::println);


        RankChecker[] testCases = new RankChecker[]{new FlushRankChecker(), new FourOfAKindRankChecker(), new FullHouseRankChecker(),
                new HighCardRankChecker(), new OnePairRankChecker(), new StraightFlushRankChecker(),
                new StraightRankChecker(), new ThreeOfAKindRankChecker(), new TwoPairRankChecker()};
        int testCaseIndex = getTestCaseIndex(scanner);

        int x = 0;
        while (true) {
            x++;
            //shuflling deck of cards
            gameController.shuffleDeck();

            //give  player cards accordingly
            gameController.dealHand(pokerVariant);

            System.out.print("Your hand:");
            //observe the cards in palyers hands
            for (Card card : gameController.getPlayerHand()) {
                System.out.print(card.getValue() + "" + card.getSuit() + " ");
            }

            //count the value of cards in player's hands and assign the strength
            if (testCaseIndex > 8) {
                System.out.println("please  enter values 0 to 8");
                testCaseIndex = getTestCaseIndex(scanner);
            }

            //RankChecker used from here for evaluating hand Strength
            handEvaluator(gameController, pokerVariant, testCases[testCaseIndex]);


            System.out.print("\nEnter 'exit' to quit or press Enter to play again: ");
            String userInput = scanner.nextLine().trim();

            testCaseIndex = getTestCaseIndex(scanner);

            //control game continuity
            if (gameController.getPokerGame().getDeck().size() < pokerVariant.getHandSize()) {
                gameController.setOriginalDeck(deckOg);
            } else if ("exit".equalsIgnoreCase(userInput.toLowerCase().trim())) {
                System.out.println("Exiting the game. Thanks for playing!");
                scanner.close();
                break;
            }

        }
    }


    //choosing test case on runtime
    private static int getTestCaseIndex(Scanner scanner) {
        int testCaseIndex;
        System.out.print("\nenter to continue or key in index value  :");
        String scan = scanner.nextLine();
        if (scan.trim().isEmpty()) {
            System.out.println("please you  not entering values from 0 to 8");
        } else {
            int testCaseIndexChanged = Integer.parseInt(scan);
            if (testCaseIndexChanged > 8) {
                System.out.println("please you  not entering values from 0 to 8");
                getTestCaseIndex(scanner);
                testCaseIndex = testCaseIndexChanged;
                return testCaseIndex;

            }
        }
        return DEFAULT_INDEX;
    }


    private static void handEvaluator(GameController gameController, PokerVariant pokerVariant, RankChecker testCases) {
        String handRank = gameController.evaluateHand(pokerVariant, testCases);
        System.out.println("\nYou have: " + handRank);
    }

}


