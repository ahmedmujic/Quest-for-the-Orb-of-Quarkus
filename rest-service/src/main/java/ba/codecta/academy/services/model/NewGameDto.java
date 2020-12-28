package ba.codecta.academy.services.model;

import ba.codecta.academy.repository.entity.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class NewGameDto {
    private Integer gameId;
    private PlayerDto player;
    private String message;
    private String mapName;
    @JsonIgnore
    private List<PlayerDto> playersOnTheMap;


    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public PlayerDto getPlayer() {

        return player;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public List<PlayerDto> getPlayersOnTheMap() {
        return playersOnTheMap;
    }

    public void setPlayersOnTheMap(List<PlayerDto> playersOnTheMap) {
        this.playersOnTheMap = playersOnTheMap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPlayer(PlayerDto player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setHealth(player.getHealth());
        playerDto.setWeapon(player.getWeapon());
        playerDto.setPlayerInventory(player.getPlayerInventory());
        this.mapName = player.getMap().getName();
        this.playersOnTheMap = player.getMap().getPlayer();
        this.message = "Get ready for the fight";
        this.player = playerDto;
    }
}
