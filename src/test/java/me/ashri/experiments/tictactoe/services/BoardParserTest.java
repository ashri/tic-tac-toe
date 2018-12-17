package me.ashri.experiments.tictactoe.services;

import me.ashri.experiments.tictactoe.entities.Board;
import me.ashri.experiments.tictactoe.entities.BoardValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class BoardParserTest {

    private BoardParser parser;

    @Before
    public void setup() {
        parser = new BoardParser();
        parser.gameEngine = new GameEngine();
    }

    @Test
    public void testParseNullInput() {
        String input = null;
        assertPatternFailure(input);
    }

    @Test
    public void testParseEmptyString() {
        String input = "";
        assertPatternFailure(input);
    }

    @Test
    public void testParseShortInput() {
        String input = "xoxoxo";
        assertPatternFailure(input);
    }

    @Test
    public void testParseLongInput() {
        String input = "xoxoxoxoxoxoxoxo";
        assertPatternFailure(input);
    }

    @Test
    public void testParseTooManyX() {
        String input = "x x x o  ";
        assertLogicFailure(input, "too many \"x\"");
    }

    @Test
    public void testParseTooManyO() {
        String input = "xoxo  o  ";
        assertLogicFailure(input, "too many \"o\"");
    }

    @Test
    public void testParseTooManyO2() {
        String input = "ox o o   ";
        assertLogicFailure(input, "too many \"o\"");
    }

    @Test
    public void testParseOWentFirst() {
        String input = "o        ";
        assertLogicFailure(input, "too many \"o\"");
    }

    @Test
    public void testParseXAlreadyWon() {
        String input = "x  xo xo ";
        assertLogicFailure(input, "\"x\" already won");
    }

    @Test
    public void testParseXAlreadyWon2() {
        String input = "xxxo  o  ";
        assertLogicFailure(input, "\"x\" already won");
    }

    @Test
    public void testParseOAlreadyWon() {
        String input = "xox ox o ";
        assertLogicFailure(input, "\"o\" already won");
    }

    @Test
    public void testParseBoardFull() {
        String input = "oxoxoxoxo";
        assertLogicFailure(input, "board already full");
    }

    @Test
    public void testParseEmptyBoard() {
        String input = "         ";
        Board board = parser.parse(input);
        Assert.assertNotNull(board);
    }

    @Test
    public void testParseEvenTurns() {
        String input = "xox   o  ";
        Board board = parser.parse(input);
        Assert.assertNotNull(board);
    }

    @Test
    public void testParseXOneTurnAhead() {
        String input = "x xox o  ";
        Board board = parser.parse(input);
        Assert.assertNotNull(board);
    }

    @Test
    public void testParseWhenUpperCase() {
        String input = "X XOX O  ";
        Board board = parser.parse(input);
        Assert.assertNotNull(board);
    }

    private void assertPatternFailure(String input) {
        try {
            parser.parse(input);
            Assert.fail("Should throw pattern validation exception for input:" + input);

        } catch (BoardValidationException e) {
            Assert.assertTrue(e.getMessage().contains("required pattern"));
        }
    }

    private void assertLogicFailure(String input, String expectedErrorContains) {
        try {
            parser.parse(input);
            Assert.fail("Should throw logic validation exception for input:" + input);

        } catch (BoardValidationException e) {
            Assert.assertTrue(e.getMessage().contains("was not valid"));
            Assert.assertTrue(e.getMessage().contains(expectedErrorContains));
        }
    }
}
