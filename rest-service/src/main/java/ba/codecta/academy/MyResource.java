package ba.codecta.academy;

import ba.codecta.academy.services.QoQService;
import ba.codecta.academy.services.model.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/api")
public class MyResource {
    public class Error {
        public String code;
        public String description;

        public Error(String code, String description) {
            this.code = code;
            this.description = description;
        }
    }
    @Inject
    QoQService QoQService;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @POST
    @Path("/game")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createGame(PlayerDto player, @Context UriInfo uriInfo){

        GameDto gameDto = QoQService.createGame(player);
      if(gameDto!=null){
          UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
          uriBuilder.path(Integer.toString(gameDto.getId()));
          return Response.created(uriBuilder.build()).entity(gameDto).build();
      }
      return Response.status(Response.Status.BAD_REQUEST).entity(new Error("VD-1","Bad request")).build();
    }

    @POST
    @Path("/game/{id}/move")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response movePlayer(@PathParam("id") Integer gameId, @Context UriInfo uriInfo)
    {
        DungeonDto currentDungeonDto = QoQService.movePlayer(gameId);
        if(currentDungeonDto != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(currentDungeonDto.getId()));
            return Response.created(uriBuilder.build()).entity(currentDungeonDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("VD-1","Bad request")).build();
    }

    @POST
    @Path("/game/{id}/fight")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response fightWithMonster(@PathParam("id") Integer gameId, AttackDto attack , @Context UriInfo uriInfo)
    {
        DungeonDto currentDungeonDto = QoQService.fightWithMonster(gameId, attack);
        if(currentDungeonDto != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(currentDungeonDto.getId()));
            return Response.created(uriBuilder.build()).entity(currentDungeonDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("VD-1","Bad request")).build();
    }
}