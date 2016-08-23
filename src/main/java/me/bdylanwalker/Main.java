package me.bdylanwalker;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Standard Game of War
 *
 * Deck is dealt one card at a time to each player.  All players have the same number of cards to start the game.
 * Play continues until only one player remains.  That player has all the cards.  A round is played by each player
 * placing a card into the pot with the highest card winning the pot.  Cards are retrieved from the pot and sorted
 * highest to lowest before being placed onto the bottom of the winners hand.  If one or more players have the same
 * rank, then a war is declared at which time each player places an extra card into the pot.  War continues until
 * there is only player with a high card. If at anytime during game play a player runs out of cards, they lose and play
 * continues among the remaining players.
 *
 */
public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);

    //mvn exec:java
    public static void main(String... args) {
        String numberOfSuits = args[0];
        String numberOfRanks = args[1];
        String numberOfPlayers = args[2];

        try {

            logger.info("BEGIN");
            new War().play(
                    //default to 4 suits, 13 ranks, and 2 players
                    NumberUtils.toInt( numberOfSuits, 4),
                    NumberUtils.toInt( numberOfRanks, 13),
                    NumberUtils.toInt( numberOfPlayers, 2)
            );

        } finally {
            logger.info("END");

        }
    }
}
