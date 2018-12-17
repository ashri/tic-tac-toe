package me.ashri.experiments.tictactoe.entities;

public class BoardValidationException extends RuntimeException {

    private BoardValidationException(String message) {
        super(message);
    }

    public static BoardValidationException patternMismatch() {
        return new BoardValidationException("The provided board does not match the required pattern \"[xo ]{9}\"");
    }

    public static BoardValidationException logicError(String message) {
        return new BoardValidationException("The provided board was not valid due to: " + message);
    }
}
