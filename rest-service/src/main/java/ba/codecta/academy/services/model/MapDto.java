package ba.codecta.academy.services.model;

import java.util.ArrayList;
import java.util.List;

public class MapDto {
    private Integer id;
    private List<DungeonDto> dungeonDtos = new ArrayList<>();
    private List<PlayerDto> playerDtos = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<DungeonDto> getDungeonDtos() {
        return dungeonDtos;
    }

    public void setDungeonDtos(List<DungeonDto> dungeonDtos) {
        this.dungeonDtos = dungeonDtos;
    }

    public List<PlayerDto> getPlayerDtos() {
        return playerDtos;
    }

    public void setPlayerDtos(List<PlayerDto> playerDtos) {
        this.playerDtos = playerDtos;
    }
}
