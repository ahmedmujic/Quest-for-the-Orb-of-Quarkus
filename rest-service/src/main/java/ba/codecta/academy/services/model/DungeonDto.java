package ba.codecta.academy.services.model;


import ba.codecta.academy.repository.entity.Items;
import ba.codecta.academy.repository.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DungeonDto {
    private Integer id;
    private String name;
    private List<MonsterDto> monsters = new ArrayList<>();
    private MapDto map;
    private List<PlayerDto> players = new ArrayList<>();
    private List<ItemsDto> items = new ArrayList<>();
    private Boolean finished;


    public DungeonDto(){}
    public DungeonDto(String name) {
        this.name = name;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

    public List<ItemsDto> getItems() {
        return items;
    }

    public void setItems(List<ItemsDto> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MapDto getMap() {
        return map;
    }

    public void setMap(MapDto map) {
        this.map = map;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MonsterDto> getMonsters() {
        return monsters;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public void setMonsters(List<MonsterDto> monsters) {
        this.monsters = monsters;
    }
}
