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
    public Response game(@QueryParam("board") String input) {
        return play(input, false);
    }

    @GET
    @Path("debug")
    public Response debugGame(@QueryParam("board") String input) {
        return play(input, true);
    }

    private Response play(String input, boolean formatBoard) {
        try {
            Board board = boardParser.parse(input);
            board = engine.nextMove(board);
            String output = formatBoard ? board.toFormattedString() : board.toString();
            return Response.ok().entity(output).build();

        } catch (BoardValidationException e) {
            return Response.status(400)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
