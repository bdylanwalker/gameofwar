package me.bdylanwalker;


import java.util.ArrayList;
import java.util.List;

public class Pot {

    List< Card> cards;

    public Pot() {
        cards = new ArrayList< Card>();

    }

    public List< Card> getCards() {
        return cards;

    }


    public void addCard( Card c) {
        cards.add( c);

    }

    @Override
    public String toString() {
        return String.format("Pot: %s", cards);

    }
}
