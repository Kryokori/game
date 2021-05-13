package game;
import java.util.*;

import game.deck.fullDeck;

import game.player.Round;

public class Application {

    public static void main(String[] args) {
        int gameStage = 0;

        System.out.println("Blackjack");
        do {
            prompCommand(gameStage);
            switch (gameStage) {
                case 0 -> {
                    fullDeck.sortDeck();
                    gameStage = Round.setPlayerNr(playerResponse());
                    generatePlayer();
                    Round.playerTurn();
                }
                case 1 -> {
                    playGame(playerResponse());
                    if (Round.endGame()) {
                        gameStage = 2;
                        Round.automaticGame();
                        Round.checkResults();
                    }
                }
                case 2 -> gameStage = gameResult(gameStage, playerResponse());
            }
        } while(gameStage != 3);
        prompCommand(gameStage);
    }

    private static String playerResponse(){
        Scanner playerInput = new Scanner(System.in);
        return playerInput.nextLine();
    }

    private static void prompCommand(int gameStage){
        switch (gameStage) {
            case 0 -> System.out.println("Chose the number of players: [1], [2], [3], [4].");
            case 1 -> System.out.println("Choose your next move: [Draw], [Pass].");
            case 2 -> System.out.println("[Play] again with the same players, [New] game, show [Results], [Exit] game.");
            default -> System.out.println("Closing game.");
        }
    }

    public static int gameResult(int gameStage, String playerCommand) {
        switch (playerCommand) {
            case "Exit":
                return 3;
            case "Play":
                Round.newRound();
                Round.playerTurn();
                return 1;
            case "Results":
                Round.getResults();
                return gameStage;
            case "New":
                Round.newGame();
                return 0;
            default:
                System.out.println("Incorrect command.");
                return gameStage;
        }
    }

    private static void playGame(String playerCommand){
        switch (playerCommand) {
            case "Pass" -> Round.passTurn();
            case "Draw" -> Round.drawCard();
            default -> System.out.println("Incorrect command.");
        }
    }

    private static void generatePlayer() {
        for (int p = 1; p <= Round.getPlayerNr(); p++) {
            System.out.println("Set name of a player nr" + p + ":");
            Round.addPlayer(playerResponse());
        }
        Round.addPlayer("Game Dealer");
    }

}
