package ba.codecta.academy.services.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class MapDto {
    private Integer id;
    @JsonIgnore
    private List<DungeonDto> dungeon = new ArrayList<>();
    @JsonIgnore
    private List<PlayerDto> player = new ArrayList<>();
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<DungeonDto> getDungeon() {
        return dungeon;
    }

    public void setDungeon(List<DungeonDto> dungeon) {
        this.dungeon = dungeon;
    }

    public List<PlayerDto> getPlayer() {
        return player;
    }

    public void setPlayer(List<PlayerDto> player) {
        this.player = player;
    }
}
