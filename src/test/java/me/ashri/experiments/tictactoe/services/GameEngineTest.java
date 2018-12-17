package me.ashri.experiments.tictactoe.services;

import me.ashri.experiments.tictactoe.entities.Board;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameEngineTest {

    private GameEngine gameEngine;

    @Before
    public void setup() {
        this.gameEngine = new GameEngine();
    }

    @Test
    public void testNoWinner() {
        Board board = new Board("         ".toCharArray());
        Assert.assertFalse(gameEngine.hasWon(board, 'x'));
        Assert.assertFalse(gameEngine.hasWon(board, 'o'));

        board = new Board("x o x o  ".toCharArray());
        Assert.assertFalse(gameEngine.hasWon(board, 'x'));
        Assert.assertFalse(gameEngine.hasWon(board, 'o'));

        board = new Board("xxooxo   ".toCharArray());
        Assert.assertFalse(gameEngine.hasWon(board, 'x'));
        Assert.assertFalse(gameEngine.hasWon(board, 'o'));
    }

    @Test
    public void testHasWonHorizontally() {
        Board board = new Board("xxx o   o".toCharArray());
        Assert.assertTrue(gameEngine.hasWon(board, 'x'));
        Assert.assertFalse(gameEngine.hasWon(board, 'o'));

        board = new Board(" o xxx  o".toCharArray());
        Assert.assertTrue(gameEngine.hasWon(board, 'x'));
        Assert.assertFalse(gameEngine.hasWon(board, 'o'));

        board = new Board(" o   oxxx".toCharArray());
        Assert.assertTrue(gameEngine.hasWon(board, 'x'));
        Assert.assertFalse(gameEngine.hasWon(board, 'o'));
    }

    @Test
    public void testHasWonVertically() {
        Board board = new Board("xo xo x o".toCharArray());
        Assert.assertTrue(gameEngine.hasWon(board, 'x'));
        Assert.assertFalse(gameEngine.hasWon(board, 'o'));

        board = new Board(" xo xo x ".toCharArray());
        Assert.assertTrue(gameEngine.hasWon(board, 'x'));
        Assert.assertFalse(gameEngine.hasWon(board, 'o'));

        board = new Board("  xo xo x".toCharArray());
        Assert.assertTrue(gameEngine.hasWon(board, 'x'));
        Assert.assertFalse(gameEngine.hasWon(board, 'o'));
    }

    @Test
    public void testHasWonDiagonally() {
        Board board = new Board("xo  xo  x".toCharArray());
        Assert.assertTrue(gameEngine.hasWon(board, 'x'));
        Assert.assertFalse(gameEngine.hasWon(board, 'o'));

        board = new Board("  xoxox  ".toCharArray());
        Assert.assertTrue(gameEngine.hasWon(board, 'x'));
        Assert.assertFalse(gameEngine.hasWon(board, 'o'));
    }

    @Test
    public void testOAlreadyWon() {
        Board board = new Board("xox ox o ".toCharArray());
        Assert.assertFalse(gameEngine.hasWon(board, 'x'));
        Assert.assertTrue(gameEngine.hasWon(board, 'o'));
    }

    @Test
    public void testNextMove() {
        Board board = new Board("         ".toCharArray());
        board = gameEngine.nextMove(board);
        Assert.assertEquals("o        ", board.toString());
    }
}