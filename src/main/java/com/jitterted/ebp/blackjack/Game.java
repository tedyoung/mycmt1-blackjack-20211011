package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

    private final Deck deck;

    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();

    public static void main(String[] args) {
        displayWelcomeScreen();
        playGame();
        resetScreen();
    }

    private static void playGame() {
        Game game = new Game();
        game.initialDeal();
        game.play();
    }

    private static void resetScreen() {
        System.out.println(ansi().reset());
    }

    private static void displayWelcomeScreen() {
        System.out.println(ansi()
                                   .bgBright(Ansi.Color.WHITE)
                                   .eraseScreen()
                                   .cursor(1, 1)
                                   .fgGreen().a("Welcome to")
                                   .fgRed().a(" Jitterted's")
                                   .fgBlack().a(" BlackJack"));
    }

    public Game() {
        deck = new Deck();
    }

    public void initialDeal() {
        dealRoundOfCards();
        dealRoundOfCards();
    }

    private void dealRoundOfCards() {
        // deal card to players first as per the rules of Blackjack
        playerHand.drawCardFrom(deck);
        dealerHand.drawCardFrom(deck);
    }

    public void play() {
        boolean playerBusted = playerTurn();

        dealerTurn(playerBusted);

        displayFinalGameState();

        determineOutcome(playerBusted);
    }

    private void determineOutcome(boolean playerBusted) {
        if (playerBusted) {
            System.out.println("You Busted, so you lose.  💸");
        } else if (dealerHand.value() > 21) {
            System.out.println("Dealer went BUST, Player wins! Yay for you!! 💵");
        } else if (dealerHand.value() < playerHand.value()) {
            System.out.println("You beat the Dealer! 💵");
        } else if (dealerHand.value() == playerHand.value()) {
            System.out.println("Push: You tie with the Dealer. 💸");
        } else {
            System.out.println("You lost to the Dealer. 💸");
        }
    }

    private void dealerTurn(boolean playerBusted) {
        // Dealer makes its choice automatically based on a simple heuristic (<=16, hit, 17>=stand)
        if (!playerBusted) {
            while (dealerHand.value() <= 16) {
                dealerHand.drawCardFrom(deck);
            }
        }
    }

    private boolean playerTurn() {
        // hit until they stand, then they're done (or they go bust)
        boolean playerBusted = false;
        while (!playerBusted) {
            displayGameState();
            String playerChoice = inputFromPlayer().toLowerCase();
            if (playerChoseStand(playerChoice)) {
                break;
            }
            if (playerChoseHit(playerChoice)) {
                playerHand.drawCardFrom(deck);
                if (playerHand.value() > 21) {
                    playerBusted = true;
                }
            } else {
                System.out.println("You need to [H]it or [S]tand");
            }
        }
        return playerBusted;
    }

    private boolean playerChoseHit(String playerChoice) {
        return playerChoice.startsWith("h");
    }

    private boolean playerChoseStand(String playerChoice) {
        return playerChoice.startsWith("s");
    }

    private String inputFromPlayer() {
        System.out.println("[H]it or [S]tand?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void displayGameState() {
        clearScreen();
        System.out.println("Dealer has: ");
        System.out.println(dealerHand.firstCard().display()); // first card is Face Up

        // second card is the hole card, which is hidden
        displayBackOfCard();

        displayPlayerHand();
    }

    private void displayPlayerHand() {
        System.out.println();
        System.out.println("Player has: ");
        playerHand.display();
        System.out.println(" (" + playerHand.value() + ")");
    }

    private void clearScreen() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
    }

    private void displayBackOfCard() {
        System.out.print(
                ansi()
                        .cursorUp(7)
                        .cursorRight(12)
                        .a("┌─────────┐").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("└─────────┘"));
    }

    private void displayFinalGameState() {
        clearScreen();
        System.out.println("Dealer has: ");
        dealerHand.display();
        System.out.println(" (" + dealerHand.value() + ")");

        displayPlayerHand();
    }
}
