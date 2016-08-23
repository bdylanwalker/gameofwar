package me.bdylanwalker;

import java.util.List;

public class Player
{
    private String name;
    private int number;
    private Hand hand;

    public Player() {}
    public Player( int number) {
        this.name = String.format( "Player %s", number);
        this.number = number;
        this.hand = new Hand();

    }

    public void giveCard( Card c) {
        this.hand.addCard( c);

    }

    public String getName() {
        return this.name;

    }

    public Hand getHand() {
        return this.hand;

    }

    public int getNumber() {
        return this.number;

    }

    @Override
    public String toString() {
        return String.format( "%s: [ %s ]", new Object[] { this.name, this.hand});

    }
}
