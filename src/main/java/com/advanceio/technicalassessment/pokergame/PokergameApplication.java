package com.advanceio.technicalassessment.pokergame;

import com.advanceio.technicalassessment.pokergame.controller.GameController;
import com.advanceio.technicalassessment.pokergame.entity.Card;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.DefaultVariant;
import com.advanceio.technicalassessment.pokergame.services.util.gamerules.PokerVariant;
import com.advanceio.technicalassessment.pokergame.services.util.rank.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PokergameApplication {


    //tests
    public static void main(String[] args) {
        SpringApplication.run(PokergameApplication.class);
        //in future  to available over the web
        Scanner scanner = new Scanner(System.in);

        //choosing poker variant of game size of 5 cards
        PokerVariant pokerVariant = new DefaultVariant();

        System.out.println("\n\nWelcome to the Five-Card Draw Poker Game!");
        int x = 0;


        RankChecker[] testCases = new RankChecker[]{new FlushRankChecker(), new FourOfAKindRankChecker(), new FullHouseRankChecker(),
                                                    new HighCardRankChecker(), new OnePairRankChecker(), new StraightFlushRankChecker(),
                                                    new StraightRankChecker(), new ThreeOfAKindRankChecker(), new TwoPairRankChecker()};

            GameController gameController = new GameController();
            while (true) {
                x++;
                gameController.shuffleDeck();

                //give  player cards accordingly
                gameController.dealHand(pokerVariant);

                System.out.print("Your hand:");
                //observe the cards in palyers hands
                for (Card card : gameController.getPlayerHand()) {
                    System.out.print(card.getValue() + "" + card.getSuit() + " ");
                }

                //count the value of cards in player's hands and assign the strength
                String handRank = gameController.evaluateHand(pokerVariant, testCases[4]);
                System.out.println("\nYou have: " + handRank);


                System.out.print("\nEnter 'exit' to quit or press Enter to play again: ");
                String userInput = scanner.nextLine().trim();

                if (x == 10) {
                    if ("exit".equalsIgnoreCase(userInput)) {
                        System.out.println("Exiting the game. Thanks for playing!");
                        scanner.close();
                        break;
                    }
                    System.out.println("Game over. Thanks for playing!");
                    break;
                }
            }

        }
    }

