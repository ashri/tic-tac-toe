package me.ashri.experiments.tictactoe;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import me.ashri.experiments.tictactoe.entities.Board;
import me.ashri.experiments.tictactoe.entities.BoardValidationException;
import me.ashri.experiments.tictactoe.services.BoardParser;
import me.ashri.experiments.tictactoe.services.GameEngine;

@ApplicationScoped
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class GameResource {

    @Inject
    BoardParser boardParser;

    @Inject
    GameEngine engine;

    @GET
    public Response game(@QueryParam("board") String boardInput) {
        try {
            Board board = boardParser.parse(boardInput);
            board = engine.nextMove(board);
            return Response.ok().entity(board.toString()).build();

        } catch (BoardValidationException e) {
            return Response.status(400)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
