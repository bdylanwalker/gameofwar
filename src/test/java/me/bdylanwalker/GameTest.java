package me.bdylanwalker;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GameTest {
    static final Logger logger = LoggerFactory.getLogger( GameTest.class);

    private FrenchDeck deck;

    @Before
    public void init() {
        deck = new FrenchDeck();
        deck.create( 4, 13);

    }

    @Test
    public void testCardCompare() {
        Card c1 = new Card( 2, 1);
        Card c2 = new Card( 3, 1);
        Card c3 = new Card( 2, 2);

        logger.debug("---");
        logger.debug( "{} < {}", c1, c2);
        assertTrue( c1.compareTo( c2) < 0 );

        logger.debug( "{} > {}", c2, c1);
        assertTrue( c2.compareTo( c1) > 0);

        logger.debug( "{} == {}", c1, c3);
        assertTrue( c1.compareTo( c3) == 0);
    }

    @Test
    public void testCardEquality() {
        Card c1 = new Card( 2, 1);
        Card c2 = new Card( 2, 2);

        assertEquals( c1, c2);
    }

    @Test
    public void testTakePot() {
        War w = new War();
        Player p1 = new Player( 1);

        Pot pot = new Pot();
        pot.addCard( new Card( 2, 1));
        pot.addCard( new Card( 13, 1));

        Pot expected = new Pot();
        expected.addCard( new Card( 13, 1));
        expected.addCard( new Card( 2, 1));

        w.setPot( pot);

        assertTrue( p1.getHand().getCards().isEmpty());

        logger.debug( "---");
        logger.debug( expected.toString());
        logger.debug( pot.toString());

        w.takePot( p1);

        assertEquals( expected.getCards(), p1.getHand().getCards());
    }

    @Test
    public void testGetWinnerNoWar() {
        War w = new War();
        Player p1 = new Player( 1);
        p1.getHand().addCard( new Card( 2, 1));
        Player p2 = new Player( 2);
        p2.getHand().addCard( new Card( 13, 1));

        logger.debug("---");
        logger.debug( p1.toString());
        logger.debug( p2.toString());

        List< Player> players = new ArrayList< Player>();
        players.add( p1);
        players.add( p2);

        w.setPlayers( players);

        List< Player> round = new ArrayList< Player>();
        round.add( p1);
        round.add( p2);

        assertEquals( p2, w.getWinner( round, Boolean.FALSE));

    }

    @Test
    public void testThreePlayerNoWar() {
        War w = new War();
        Player p1 = new Player( 1);
        p1.getHand().addCard( new Card( 2, 1));
        Player p2 = new Player( 2);
        p2.getHand().addCard( new Card( 13, 1));
        Player p3 = new Player( 3);
        p3.getHand().addCard( new Card( 9, 1));

        logger.debug("---");
        logger.debug( p1.toString());
        logger.debug( p2.toString());
        logger.debug( p3.toString());

        List< Player> players = new ArrayList< Player>();
        players.add( p1);
        players.add( p2);
        players.add( p3);

        w.setPlayers( players);

        List< Player> round = new ArrayList< Player>();
        round.add( p1);
        round.add( p2);
        round.add( p3);

        assertEquals( p2, w.getWinner( round, Boolean.FALSE));

    }

    @Test
    public void testGetWinnerWithWar() {
        War w = new War();
        Player p1 = new Player( 1);
        p1.getHand().addCard( new Card( 2, 1));
        p1.getHand().addCard( new Card( 3, 1));
        p1.getHand().addCard( new Card( 4, 1));

        Player p2 = new Player( 2);
        p2.getHand().addCard( new Card( 2, 1));
        p2.getHand().addCard( new Card( 3, 1));
        p2.getHand().addCard( new Card( 13, 1));

        logger.debug("---");
        logger.debug( p1.toString());
        logger.debug( p2.toString());

        List< Player> players = new ArrayList< Player>();
        players.add( p1);
        players.add( p2);

        w.setPlayers( players);

        List< Player> round = new ArrayList< Player>();
        round.add( p1);
        round.add( p2);

        assertEquals( p2, w.getWinner( round, Boolean.FALSE));

    }

    @Test
    public void testThreePlayerWithDoubleWar() {
        War w = new War();
        Player p1 = new Player( 1);
        p1.getHand().addCard( new Card( 3, 1));
        p1.getHand().addCard( new Card( 4, 1));
        p1.getHand().addCard( new Card( 4, 1));
        p1.getHand().addCard( new Card( 4, 1));
        p1.getHand().addCard( new Card( 4, 1));


        Player p2 = new Player( 2);
        p2.getHand().addCard( new Card( 3, 1));
        p2.getHand().addCard( new Card( 4, 1));
        p2.getHand().addCard( new Card( 4, 1));
        p2.getHand().addCard( new Card( 13, 1));
        p2.getHand().addCard( new Card( 13, 1));

        Player p3 = new Player( 3);
        p3.getHand().addCard( new Card( 3, 1));
        p3.getHand().addCard( new Card( 4, 1));
        p3.getHand().addCard( new Card( 2, 1));
        p3.getHand().addCard( new Card( 2, 1));
        p3.getHand().addCard( new Card( 4, 1));

        logger.debug("---");
        logger.debug( p1.toString());
        logger.debug( p2.toString());
        logger.debug( p3.toString());

        List< Player> players = new ArrayList< Player>();
        players.add( p1);
        players.add( p2);
        players.add( p3);

        w.setPlayers( players);

        List< Player> round = new ArrayList< Player>();
        round.add( p1);
        round.add( p2);
        round.add( p3);

        assertEquals( p2, w.getWinner( round, Boolean.FALSE));

    }

    @Test
    public void testSize() {
        assertEquals( 52, deck.getCards().size());

    }

    @Test
    public void testShuffle() {
        int sizeBefore = deck.getCards().size();

        logger.debug( deck.getCards().toString());
        deck.shuffle();
        logger.debug( deck.getCards().toString());

        int sizeAfter = deck.getCards().size();

        assertEquals( sizeBefore, sizeAfter);

    }

}
