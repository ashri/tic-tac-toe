package me.ashri.experiments.tictactoe.services;

import javax.enterprise.context.RequestScoped;

import me.ashri.experiments.tictactoe.entities.Board;
import me.ashri.experiments.tictactoe.entities.BoardValidationException;

@RequestScoped
public class BoardParser {

    public Board parse(String boardInput) {
        validateInput(boardInput);

        return new Board();
    }

    private void validateInput(String boardInput) {
        if (boardInput == null || !boardInput.matches("[xo ]{9}")) {
            throw BoardValidationException.patternMismatch();
        }

    }

}
