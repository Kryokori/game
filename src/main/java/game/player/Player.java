package game.player;

import game.deck.Card;

import java.util.ArrayList;
import java.util.List;

public class Player{
    String playerName;
    private List<Card> drawnCard;
    private int accumulatedPoints;
    private int result;

    public Player(String playerName, int accumulatedPoints, List<Card> drawnCard, int result) {
        this.playerName = playerName;
        this.accumulatedPoints = accumulatedPoints;
        this.drawnCard = drawnCard;
        this.result = result;
    }

    public Player(String playerName) {
        this(playerName, 0, new ArrayList<>(), 0);
    }

    public void resetPlayerData(){
        accumulatedPoints = 0;
        result = 0;
        drawnCard.clear();
    }

    private void blackJack() { System.out.println(playerName + " calls a blackjack!");}

    private boolean checkAces() {
        if(drawnCard.size() == 2) {
            for (Card card : drawnCard){
                if (!card.getCardName().equals("Ace")) {return false;}
            }
            blackJack();
            return true;
        }
        return false;
    }

    public void countPoints() {
        if (checkAces()) {
            accumulatedPoints = 22;
            result = 1;
        } else {
            accumulatedPoints = 0;
            for (Card card : drawnCard) {
                accumulatedPoints += card.getCardValue();
                if (accumulatedPoints == 21) {
                    blackJack();
                    result = 2;
                }
                else if (accumulatedPoints > 21) {
                    System.out.println(playerName + " accumulated " + accumulatedPoints + " points and loses game.");
                    result = 4;
                }
            }
        }
    }

    public void passGame(){ result = 3;}

    public void typeCards() {
        StringBuilder text = new StringBuilder();
        text.append("Cards of " + playerName + ": ");
        for (Card card : drawnCard) {
            text.append(card.getCardTitle());
        }
        System.out.println(text);
    }

    public void addCard(Card card) {
        drawnCard.add(card);
    }

    public int getPoints() { return accumulatedPoints; }

    public int getResult() { return result; }

    public boolean ifContinue() { return result==0;}
}
