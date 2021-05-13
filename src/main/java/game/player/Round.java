package game.player;

import game.deck.fullDeck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Round {
    private static List<Player> players = new ArrayList<>();
    private static int playerNr = 0;
    private static int turn = 0;
    private static ArrayList<String> results = new ArrayList<>();

    public static void newGame(){
        players.clear();
        results.clear();
        turn = 0;
        playerNr = 0;
    }

    public static void newRound(){
        turn = 0;
        for (Player player : players) {
            player.resetPlayerData();
        }
    }

    public static int setPlayerNr(String playerCommand) {
        newRound();
        switch (playerCommand) {
            case "1" -> {
                playerNr = 1;
                return 1;
            }
            case "2" -> {
                playerNr = 2;
                return 1;
            }
            case "3" -> {
                playerNr = 3;
                return 1;
            }
            case "4" -> {
                playerNr = 4;
                return 1;
            }
            default -> {
                System.out.println("Incorrect command.");
                return 0;
            }
        }
    }
    public static boolean endGame(){
        return (playerNr == turn);
    }

    public static void checkResults(){
        List<Player> check = players;
        Player dealer = check.get(check.size() - 1);
        check.sort(Comparator.comparing(Player::getResult));
        Player player = check.get(0);
        String text = player.playerName + " has won the game.";
        if (dealer.getResult() == player.getResult()) {
            if (dealer.getPoints() > player.getPoints()) {
            results.add(dealer.playerName + " has won the game.");
            }
        }
        results.add(text);
        System.out.println(results.get(results.size() - 1));
    }

    public static void getResults() {
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static void playerTurn(){
        System.out.println(currentPlayer().playerName + " turn.");
    }

    public static int getPlayerNr(){ return playerNr;}

    public static void addPlayer(String playerCommand){
        players.add(new Player(playerCommand));
    }

    private static Player currentPlayer(){ return players.get(turn); }

    public static void passTurn() {
        currentPlayer().passGame();
        turn++;
        playerTurn();
    }

    public static void drawCard() {
        currentPlayer().addCard(fullDeck.drawCard());
        currentPlayer().typeCards();
        currentPlayer().countPoints();
        if (!currentPlayer().ifContinue()) {
            passTurn();
        }
    }

    public static void automaticGame(){
        int previousResult = 10;
        for (Player player: players) {
            if (player.getResult() < 4) {
                previousResult = 17;
                break;
            }
        }
        do {
        currentPlayer().addCard(fullDeck.drawCard());
        currentPlayer().countPoints();
        } while (previousResult > currentPlayer().getPoints());
        currentPlayer().typeCards();
    }

}
