package me.ashri.experiments.tictactoe.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Board implements Serializable {

    private final char[] values;
    private final Set<Integer> crosses;
    private final Set<Integer> naughts;
    private final Set<Integer> empty;

    public Board(char[] values) {
        this.values = values;
        this.crosses = new HashSet<>(9);
        this.naughts = new HashSet<>(9);
        this.empty = new HashSet<>(9);
        process(values);
    }

    private void process(char[] values) {
        for (int i = 0; i < values.length; i++) {
            char c = values[i];
            switch (c) {
                case 'x':
                    crosses.add(i);
                    break;
                case 'o':
                    naughts.add(i);
                    break;
                default:
                    empty.add(i);
                    break;
            }
        }
    }

    public int getCrossCount() {
        return crosses.size();
    }

    public int getNaughtCount() {
        return naughts.size();
    }

    public int getEmptyCount() {
        return empty.size();
    }

    public Set<Integer> getCrosses() {
        return crosses;
    }

    public Set<Integer> getNaughts() {
        return naughts;
    }

    public Set<Integer> getEmpty() {
        return empty;
    }

    @Override
    public String toString() {
        return new String(values);
    }

}
