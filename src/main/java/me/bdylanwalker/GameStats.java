package me.bdylanwalker;

import org.apache.commons.lang3.time.StopWatch;

public class GameStats {
    private int wars;
    private int rounds;
    private StopWatch timer;

    public GameStats() {
        wars = 0;
        rounds = 0;
        timer = new StopWatch();
        timer.start();

    }

    public int getRounds() {
        return rounds;

    }

    public void incWars() {
        wars++;

    }

    public void incRounds() {
        rounds++;

    }

    @Override
    public String toString() {
        timer.stop();
        StringBuilder sb = new StringBuilder();
        sb.append( " [ ");
        sb.append( String.format( "%s ROUNDS, ", rounds));
        sb.append( String.format( "%s WARS, ", wars));
        sb.append( String.format( "TOOK %s", timer.toString()));
        sb.append( " ] ");
        return sb.toString();
    }
}
