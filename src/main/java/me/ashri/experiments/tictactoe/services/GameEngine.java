package me.ashri.experiments.tictactoe.services;

import java.util.*;

import javax.enterprise.context.RequestScoped;

import me.ashri.experiments.tictactoe.entities.Board;
import me.ashri.experiments.tictactoe.entities.BoardValidationException;

@RequestScoped
public class GameEngine {

    private final Set<Integer[]> WINNING_CONDITIONS;
    private final Map<Integer, Integer> WEIGHTS;

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

        WEIGHTS = new HashMap<>();
        WEIGHTS.put(0, 2);
        WEIGHTS.put(1, 1);
        WEIGHTS.put(2, 2);
        WEIGHTS.put(3, 1);
        WEIGHTS.put(4, 3);
        WEIGHTS.put(5, 1);
        WEIGHTS.put(6, 2);
        WEIGHTS.put(7, 1);
        WEIGHTS.put(8, 2);
    }

    public Board nextMove(Board board) {
        Set<Integer> empties = board.getEmpty();
        if (empties.isEmpty()) {
            throw BoardValidationException.logicError("no moves available");
        }

        Optional<Integer> positionToPlay = checkForWin(board.getNaughts(), board.getEmpty());
        if (positionToPlay.isPresent()) {
            return updateBoard(board, positionToPlay.get());
        }

        positionToPlay = checkForWin(board.getCrosses(), board.getEmpty());
        if (positionToPlay.isPresent()) {
            return updateBoard(board, positionToPlay.get());
        }

        positionToPlay = playBestWeight(board);
        return updateBoard(board, positionToPlay.get());
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

    private Optional<Integer> checkForWin(Set<Integer> moves, Set<Integer> empties) {
        for (Integer[] winCondition : WINNING_CONDITIONS) {
            Set<Integer> matchedWinConditions = new TreeSet<>();
            for (Integer wc : winCondition) {
                matchedWinConditions.add(wc);
                if (moves.contains(wc)) {
                    matchedWinConditions.remove(wc);
                }
            }
            if (matchedWinConditions.size() == 1) {
                Integer remainingWinCondition = matchedWinConditions.iterator().next();
                if (empties.contains(remainingWinCondition)) {
                    return Optional.of(remainingWinCondition);
                }
            }
        }

        return Optional.empty();
    }

    private Optional<Integer> checkToBlockWin(Board board) {
        return Optional.empty();
    }

    Optional<Integer> playBestWeight(Board board) {
        Set<Integer> empties = board.getEmpty();
        List<Integer> weightedEmpties = new ArrayList<>(empties);
        weightedEmpties.sort((i1, i2) -> WEIGHTS.get(i2).compareTo(WEIGHTS.get(i1)));
        return Optional.of(weightedEmpties.get(0));

    }

    private Board updateBoard(Board board, Integer positionToPlay) {
        char[] values = board.getValues();
        values[positionToPlay] = 'o';
        return new Board(values);
    }
}
