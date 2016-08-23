package me.bdylanwalker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class War {
    static final Logger logger = LoggerFactory.getLogger(War.class);
    static final int MAX_ROUNDS = 100000;
    private List<Player> players;
    private Pot pot;
    private GameStats stats;



    public War() {
        players = new ArrayList<Player>();
        pot = new Pot();
        stats = new GameStats();

    }

    /**
     * A player wins all the cards currently in the pot.
     *
     * @param p the Player which won the pot
     *
     */
    public void takePot(Player p) {
        //sort take is quickest, see http://drzeus.best.vwh.net/war/
        List< Card> c = pot.getCards();
        Collections.sort( c);
        Collections.reverse( c);
        logger.debug( "Pot : {}", c);

        p.getHand().addCard( c);

        pot.getCards().clear();

    }


    /**
     * Find a winner from a list of players by comparing each card and handling wars.
     *
     * @param round java.util.List of Player objects to evaluate for a winner
     * @param atWar indicates we have been called following a round in which there was more than one winner
     * @return      Player object for the winner
     */
    public Player getWinner(List<Player> round, Boolean atWar) {
        Card highCard = new Card();
        List<Player> winners = new ArrayList<Player>();
        if (1 == round.size() || 1 == players.size()) {
            return round.get( 0);
        }

        //discard one card for each war'ing player
        if (atWar) {
            logger.debug( "WAR");
            stats.incWars();
            stats.incRounds();

            for (Player p : round) {
                try {
                    pot.addCard(p.getHand().removeCard());

                } catch (IndexOutOfBoundsException e) {
                    //player loses when they run out of cards
                    players.remove( p);
                    logger.debug( "REMOVE {}", p);

                }

            }
        }

        for (Player p : round) {

            try {
                Card cur = p.getHand().removeCard();
                pot.addCard(cur);

                logger.debug( "{} has a {}", p.getName(), cur);

                if (cur.compareTo(highCard) > 0) {
                    highCard = cur;
                    winners.clear();
                    winners.add(p); //new highcard winner

                } else if (cur.compareTo(highCard) == 0) {
                    winners.add(p); //another highcard winner for war

                }

            } catch (IndexOutOfBoundsException e) {
                //player loses when they run out of cards
                players.remove(p);
                logger.debug( "REMOVE {}", p);

            }

        }

        if (winners.size() > 1) {
            atWar = Boolean.TRUE;

        }

        return getWinner( winners, atWar); //recurse until only one winner

    }


    /**
     * Main method to play a game of war.
     *
     * @param numberOfSuits     number of suits in a deck
     * @param numberOfRanks     number of ranks in a deck
     * @param numberOfPlayers   number of players in the game
     */
    public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
        logger.info( "Number of Suits {}", numberOfSuits);
        logger.info( "Number of Ranks {}", numberOfRanks);
        logger.info( "Number of Players {}", numberOfPlayers);

        //ensure we have a valid set of conditions for a game
        if ( ((numberOfSuits * numberOfRanks) / numberOfPlayers) < 3) {
            logger.error( "Each player needs at least 3 cards.");
            return;

        }

        FrenchDeck deck = new FrenchDeck();
        deck.create( numberOfSuits, numberOfRanks);
        deck.shuffle();

        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add( new Player(i));

        }

        //deal cards to each player
        while ( deck.getCards().size() >= numberOfPlayers) {
            for ( Player p : players) {
                p.giveCard(deck.deal());

            }
        }

        //play continues until there is one winner
        while ( players.size() > 1) {
            stats.incRounds();

            List<Player> round = new ArrayList<Player>();
            for (Player p : players) {
                logger.debug( p.toString());
                round.add(p);

            }

            Player winner = getWinner(round, Boolean.FALSE);
            takePot( winner);

            logger.debug( "Round Winner: {}", winner.toString());

            //account for possibility of inifite rounds
            if (stats.getRounds() > MAX_ROUNDS && players.size() > 1) {
                determineWinnerForDraw( );
                break;

            }

        }

        logger.info("STATS: {}", stats.toString());
        logger.info("WINNER: {}", players.get(0).getName());


    }

    /*
     * call the game a draw and set winner to be whoever has the most cards
     */
    private void determineWinnerForDraw() {
        logger.info( "Calling a draw");
        int highCardCount = 0;
        Player winner = new Player();
        for (Player p : players) {
            int cardCount = p.getHand().getCards().size();
            if (  cardCount > highCardCount) {
                highCardCount = cardCount;
                winner = p;

            }
        }

        players.clear();
        players.add( winner);

    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers( List<Player> players) {
        this.players = players;
    }

    public Pot getPot() {
        return pot;
    }

    public void setPot( Pot pot) {
        this.pot = pot;
    }


}
