package me.ashri.experiments.tictactoe.services;

import javax.enterprise.context.RequestScoped;

import me.ashri.experiments.tictactoe.entities.Board;
import me.ashri.experiments.tictactoe.entities.BoardValidationException;

@RequestScoped
public class BoardParser {

    public Board parse(String boardInput) {
        if (boardInput != null) {
            boardInput = boardInput.toLowerCase();
        }
        validateInput(boardInput);

        char[] input = boardInput.toCharArray();

        Board board = new Board(input);

        validateLogic(board);

        return board;
    }

    private void validateInput(String boardInput) {
        if (boardInput == null || !boardInput.matches("[xo ]{9}")) {
            throw BoardValidationException.patternMismatch();
        }
    }

    private void validateLogic(Board board) {
        if (board.getEmptyCount() == 0) {
            throw BoardValidationException.logicError("board already full");
        }
        int crossCount = board.getCrossCount();
        int naughtCount = board.getNaughtCount();

        if (naughtCount > crossCount) {
            throw BoardValidationException.logicError("too many \"o\"");
        }
        if (crossCount > (naughtCount + 1)) {
            throw BoardValidationException.logicError("too many \"x\"");
        }
    }

}
