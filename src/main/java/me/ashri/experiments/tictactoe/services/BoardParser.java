package me.ashri.experiments.tictactoe.services;

import javax.enterprise.context.RequestScoped;

import me.ashri.experiments.tictactoe.entities.Board;

@RequestScoped
public class BoardParser {

    public Board parse(String boardInput) {
        return new Board();
    }

}
