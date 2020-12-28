package ba.codecta.academy.services.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class PlayerDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double healingPoting = 100.0;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double health = 1000.0;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double score = 0.0;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private InventoryDto playerInventory;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DungeonDto currentDungeon;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    private WeaponDto weapon;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MapDto map;
    @JsonIgnore
    private List<GameDto> game;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer gameId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer map_Id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer dungeon_Id;

    private Double powerBoost = 1.0;

    public void setGame(List<GameDto> game) {
        this.game = game;
    }

    public List<GameDto> getGame() {
        return game;
    }


    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getMap_Id() {
        return map_Id;
    }

    public void setMap_Id(Integer mapId) {
        this.map_Id = mapId;
    }

    public Integer getDungeon_Id() {
        return dungeon_Id;
    }

    public void setDungeon_Id(Integer dungeonId) {
        this.dungeon_Id = dungeonId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getHealingPoting() {
        return healingPoting;
    }

    public void setHealingPoting(Double healingPoting) {
        this.healingPoting = healingPoting;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public Double getScore() {
        return score;
    }

    public Double getPowerBoost() {
        return powerBoost;
    }

    public void setPowerBoost(Double powerBoost) {
        this.powerBoost = powerBoost;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public InventoryDto getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(InventoryDto playerInventory) {
        this.playerInventory = playerInventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DungeonDto getCurrentDungeon() {
        return currentDungeon;
    }

    public void setCurrentDungeon(DungeonDto currentDungeon) {
        this.currentDungeon = currentDungeon;
    }

    public WeaponDto getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponDto weapon) {
        this.weapon = weapon;
    }

    public MapDto getMap() {
        return map;
    }

    public void setMap(MapDto map) {
        this.map = map;
    }
}
