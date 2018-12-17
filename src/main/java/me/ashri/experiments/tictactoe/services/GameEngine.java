package me.ashri.experiments.tictactoe.services;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.RequestScoped;

import me.ashri.experiments.tictactoe.entities.Board;
import me.ashri.experiments.tictactoe.entities.BoardValidationException;

@RequestScoped
public class GameEngine {

    private final Set<Integer[]> WINNING_CONDITIONS;

    public GameEngine() {
        WINNING_CONDITIONS = new HashSet<>();
        // Horizontal
        WINNING_CONDITIONS.add(new Integer[]{0, 1, 2});
        WINNING_CONDITIONS.add(new Integer[]{3, 4, 5});
        WINNING_CONDITIONS.add(new Integer[]{6, 7, 8});
        // Diagonal
        WINNING_CONDITIONS.add(new Integer[]{0, 4, 8});
        WINNING_CONDITIONS.add(new Integer[]{2, 4, 6});
        // Vertical
        WINNING_CONDITIONS.add(new Integer[]{0, 3, 6});
        WINNING_CONDITIONS.add(new Integer[]{1, 4, 7});
        WINNING_CONDITIONS.add(new Integer[]{2, 5, 8});
    }

    public Board nextMove(Board board) {
        Set<Integer> empties = board.getEmpty();
        if (empties.isEmpty()) {
            throw BoardValidationException.logicError("no moves available");
        }

        int firstEmpty = empties.iterator().next();
        char[] values = board.getValues();
        values[firstEmpty] = 'o';
        return new Board(values);
    }

    boolean hasWon(Board board, char player) {
        Set<Integer> moves = 'x' == player ? board.getCrosses() : board.getNaughts();

        for (Integer[] winCondition : WINNING_CONDITIONS) {
            boolean matchedWin = true;
            for (Integer winningMove : winCondition) {
                if (!moves.contains(winningMove)) {
                    matchedWin = false;
                }
            }
            if (matchedWin) {
                return true;
            }
        }

        return false;
    }
}
