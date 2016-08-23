package me.bdylanwalker;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Card implements Comparable< Card>
{

    private int rank;
    private int suit;

    public Card() {
        rank = 0;
        suit = 0;

    }

    public Card( int rank, int suit) {
        this.rank = rank;
        this.suit = suit;

    }

    public int getRank() {
        return rank;

    }

    public int getSuit() {
        return suit;

    }

    public int compareTo( Card other) {
        return Integer.compare( this.rank, other.rank);

    }

    @Override
    public String toString() {
        return String.format("%s-%s", new Object[] { rank, suit});

    }

    @Override
    public boolean equals( Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;

        }
        Card rhs = (Card) obj;
        return new EqualsBuilder()
                .append(rank, rhs.rank)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append( rank).
                toHashCode();

    }

}
