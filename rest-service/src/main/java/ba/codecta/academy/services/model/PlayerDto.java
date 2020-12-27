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
    private Integer score = 0;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private InventoryDto playerInventory;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DungeonDto currentDungeon;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private WeaponDto weapon;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MapDto map;
    private List<GameDto> game;

    public void setGame(List<GameDto> game) {
        this.game = game;
    }

    public List<GameDto> getGame() {
        return game;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
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
