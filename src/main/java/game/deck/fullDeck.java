package game.deck;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public abstract class fullDeck {
    private static List<Card> Deck = new ArrayList<>();
    static Queue<Card> newDeck = new ArrayDeque<>();

    private static void getFullDeck() {
        for (int s=0; s<4; s++){
            for (int t = 0; t<13; t++) {
                Deck.add(new Card(s, t));
            }
        }
    }

    public static void sortDeck(){
        getFullDeck();
        List<Card> deck = Deck;
        newDeck.clear();
        do {
            int rand = (int) ((Math.random() * deck.size()));
            newDeck.add(deck.remove(rand));
        } while (deck.size()!=0);
    }

    public static Card drawCard() {
        return newDeck.remove();
    }
}