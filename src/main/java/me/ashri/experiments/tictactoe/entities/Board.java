package me.ashri.experiments.tictactoe.entities;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class Board implements Serializable {

    private final char[] values;
    private final Set<Integer> crosses;
    private final Set<Integer> naughts;
    private final Set<Integer> empty;

    public Board(char[] values) {
        this.values = values;
        this.crosses = new TreeSet<>();
        this.naughts = new TreeSet<>();
        this.empty = new TreeSet<>();
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

    public char[] getValues() {
        return values;
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

    public String toFormattedString() {
        StringBuilder out = new StringBuilder();
        out.append(values[0]);
        out.append('|');
        out.append(values[1]);
        out.append('|');
        out.append(values[2]);
        out.append("\n-+-+-\n");
        out.append(values[3]);
        out.append('|');
        out.append(values[4]);
        out.append('|');
        out.append(values[5]);
        out.append("\n-+-+-\n");
        out.append(values[6]);
        out.append('|');
        out.append(values[7]);
        out.append('|');
        out.append(values[8]);
        return out.toString();
    }
}
