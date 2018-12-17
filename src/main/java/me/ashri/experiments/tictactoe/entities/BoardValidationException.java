package me.ashri.experiments.tictactoe.entities;

public class BoardValidationException extends RuntimeException {

    private BoardValidationException(String message) {
        super(message);
    }

    public static BoardValidationException patternMismatch() {
        return new BoardValidationException("The provided board does not match the required pattern \"[xo ]{9}\"");
    }
}
