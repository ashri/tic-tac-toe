package me.ashri.experiments.tictactoe;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class GameResource {

    @GET
    public Response ping() {
        return Response.ok().entity("Hello").build();
    }
}
