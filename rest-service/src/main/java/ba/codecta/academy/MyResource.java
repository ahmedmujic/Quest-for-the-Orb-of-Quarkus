package ba.codecta.academy;

import ba.codecta.academy.repository.entity.Player;
import ba.codecta.academy.services.QoQService;
import ba.codecta.academy.services.model.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

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

        NewGameDto gameDto = QoQService.createGame(player);
      if(gameDto!=null){
          UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
          uriBuilder.path(Integer.toString(gameDto.getGameId()));
          return Response.created(uriBuilder.build()).entity(gameDto).build();
      }
      return Response.status(Response.Status.BAD_REQUEST).entity(new Error("VD-1","Bad request")).build();
    }

    @POST
    @Path("/game/{id}/move")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response movePlayer(@PathParam("id") Integer gameId, @Context UriInfo uriInfo)
    {
        MovePlayerDto currentDungeonDto = QoQService.movePlayer(gameId);
        if(currentDungeonDto != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(Integer.toString(currentDungeonDto.getDungeonId()));
            return Response.created(uriBuilder.build()).entity(currentDungeonDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("VD-1","Bad request")).build();
    }

    @POST
    @Path("/game/{id}/fight")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response fightWithMonster(@PathParam("id") Integer gameId, AttackDto attack , @Context UriInfo uriInfo)
    {
        FightResponseDto currentDungeonDto = QoQService.fightWithMonster(gameId, attack);
        if(currentDungeonDto != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.build()).entity(currentDungeonDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("VD-1","Bad request")).build();
    }
    @POST
    @Path("/game/{id}/flee")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response fleeMapDungeon(@PathParam("id") Integer gameId, @Context UriInfo uriInfo)
    {
        FleeResponseDto fleeResponseDto = QoQService.fleeDungeon(gameId);
        if(fleeResponseDto != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.build()).entity(fleeResponseDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("VD-1","Bad request")).build();
    }

    @GET
    @Path("/player/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGifts(@PathParam("id") Integer id)
    {
        PlayerDto playerDto = QoQService.getPlayerById(id);
        if(playerDto == null ) {
            return Response.noContent().build();
        }
        return Response.ok(playerDto).build();
    }

    @POST
    @Path("/game/{id}/collect-items")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response collectDungeonItems(@PathParam("id") Integer gameId, @Context UriInfo uriInfo)
    {
        List<ItemsDto> itemsDto = QoQService.collectItems(gameId);
        if(itemsDto != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.ok(uriBuilder.build()).entity(itemsDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("VD-1","Bad request")).build();
    }

    @POST
    @Path("/game/{id}/change-weapon")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response changePlayerWeapon(@PathParam("id") Integer gameId, Integer weaponId,@Context UriInfo uriInfo)
    {
        WeaponDto weaponDto = QoQService.choosePlayerWeapon(gameId, weaponId);
        if(weaponDto != null){
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.ok(uriBuilder.build()).entity(weaponDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error("VD-1","Bad request")).build();
    }

}