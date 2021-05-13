package game.deck;

enum cardType {
    TWO("2", 2),
    THREE("3",3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10", 10),
    VALET("Valet", 2),
    QUEEN("Queen", 3),
    KING("King", 4),
    ACE("Ace", 11);

    private final String cardName;
    private final int cardValue;

    cardType(String cardName, int cardValue) {
        this.cardName = cardName;
        this.cardValue = cardValue;
    }

    public String getCardName() { return cardName; }

    public int getCardValue() { return cardValue; }
}

enum Symbols {
    HEART(" of ♡"),
    CARO(" of ♢"),
    SPADE(" of ♠"),
    TREFL(" of ♣");

    private final String cardSymbol;

    Symbols(String cardSymbol) { this.cardSymbol = cardSymbol;}

    public String getCardSymbol() { return cardSymbol; }
}

public class Card {
    private final Symbols CardSymbol;
    private cardType CardType;

    public Card(int Symbol, int Type) {
        this.CardSymbol = Symbols.values()[Symbol];
        this.CardType = cardType.values()[Type];
    }

    public String getCardName() {
        return CardType.getCardName();
    }

    public String getCardTitle() { return ("[" + this.CardType.getCardName() + this.CardSymbol.getCardSymbol() + "] ");}

    public int getCardValue() {
        return CardType.getCardValue();
    }
}