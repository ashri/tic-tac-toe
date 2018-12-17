package me.ashri.experiments.tictactoe.services;

import me.ashri.experiments.tictactoe.entities.BoardValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardParserTest {

    private BoardParser parser;

    @Before
    public void setup() {
        parser = new BoardParser();
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

    private void assertPatternFailure(String input) {
        try {
            parser.parse(input);
            Assert.fail("Should throw validation exception for input:" + input);

        } catch (BoardValidationException e) {
            Assert.assertTrue(e.getMessage().contains("required pattern"));
        }
    }
}
