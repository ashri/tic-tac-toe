package me.ashri.experiments.tictactoe.services;

import javax.enterprise.context.RequestScoped;

import me.ashri.experiments.tictactoe.entities.Board;

@RequestScoped
public class GameEngine {

    public Board nextMove(Board board) {
        return board;
    }
}
