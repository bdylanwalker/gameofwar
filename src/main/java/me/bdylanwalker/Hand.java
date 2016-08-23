package me.bdylanwalker;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards;

    public Hand() {
        cards = new ArrayList< Card>();
    }
    public Hand( List< Card> cards) {
        this.cards = cards;

    }

    public Card removeCard() {
        return cards.remove( 0);

    }

    public List< Card> getCards() {
        return cards;

    }

    public void addCard( Card c) {
        this.cards.add( c);

    }

    public void addCard( List< Card> cards) {
        this.cards.addAll( cards);

    }

    @Override
    public String toString() {
        return StringUtils.join( cards, ", ");
    }
}
