package me.ashri.experiments.tictactoe.services;

import java.util.Optional;

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
        Assert.assertEquals("    o    ", board.toString());
    }

    @Test
    public void testPlayBestWeightAtCenter() {
        Board board = new Board("         ".toCharArray());
        Optional<Integer> positionToPlay = gameEngine.playBestWeight(board);
        Assert.assertTrue(positionToPlay.isPresent());
        Assert.assertEquals(new Integer(4), positionToPlay.get());
    }

    @Test
    public void testPlayBestWeightCornerWhenCenter() {
        Board board = new Board("    x    ".toCharArray());
        Optional<Integer> positionToPlay = gameEngine.playBestWeight(board);
        Assert.assertTrue(positionToPlay.isPresent());
        Assert.assertEquals(new Integer(0), positionToPlay.get());
    }

    @Test
    public void testPlayBestWeightCornerWhenCornerAndCenter() {
        Board board = new Board("o   x    ".toCharArray());
        Optional<Integer> positionToPlay = gameEngine.playBestWeight(board);
        Assert.assertTrue(positionToPlay.isPresent());
        Assert.assertEquals(new Integer(2), positionToPlay.get());
    }

    @Test
    public void testPlayWinningMove() {
        Board board = new Board("o o x   x".toCharArray());
        board = gameEngine.nextMove(board);
        Assert.assertEquals("ooo x   x", board.toString());

        board = new Board("ox ox   x".toCharArray());
        board = gameEngine.nextMove(board);
        Assert.assertEquals("ox ox o x", board.toString());

        board = new Board(" x oo   x".toCharArray());
        board = gameEngine.nextMove(board);
        Assert.assertEquals(" x ooo  x", board.toString());

        board = new Board("    oxox ".toCharArray());
        board = gameEngine.nextMove(board);
        Assert.assertEquals("  o oxox ", board.toString());
    }

    @Test
    public void testPlayBlockingMove() {
        Board board = new Board("x x o   o".toCharArray());
        board = gameEngine.nextMove(board);
        Assert.assertEquals("xox o   o", board.toString());

        board = new Board("xo x    o".toCharArray());
        board = gameEngine.nextMove(board);
        Assert.assertEquals("xo x  o o", board.toString());

        board = new Board(" o xx   o".toCharArray());
        board = gameEngine.nextMove(board);
        Assert.assertEquals(" o xxo  o", board.toString());

        board = new Board("    xoxo ".toCharArray());
        board = gameEngine.nextMove(board);
        Assert.assertEquals("  o xoxo ", board.toString());
    }
}