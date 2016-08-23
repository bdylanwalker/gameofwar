package me.bdylanwalker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrenchDeck implements Deck {
    final Logger logger = LoggerFactory.getLogger( FrenchDeck.class);

    private List<Card> cards;

    public FrenchDeck() {
        cards = new ArrayList< Card>();

    }

    //maybe they want to add in a joker
    public void create( int numberOfSuits, int numberOfRanks ) {

        for (int r = 1; r <= numberOfRanks; r++) {
            for (int s = 1; s <= numberOfSuits; s++) {

                cards.add( new Card( r, s));

            }
        }

    }

    public void shuffle() {
        Collections.shuffle( cards);

    }

    public Card deal() {
        return cards.remove( 0);

    }

    public List<Card> getCards() {
        return cards;

    }
}
