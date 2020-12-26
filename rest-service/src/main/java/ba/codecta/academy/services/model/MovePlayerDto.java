package ba.codecta.academy.services.model;

import ba.codecta.academy.repository.entity.Items;
import ba.codecta.academy.repository.entity.Monster;

import java.util.List;

public class MovePlayerDto {
    private Integer dungeonId;
    private List<MonsterDto> monsters;
    private List<ItemsDto> items;

    public Integer getDungeonId() {
        return dungeonId;
    }

    public void setDungeonId(Integer dungeonId) {
        this.dungeonId = dungeonId;
    }

    public List<MonsterDto> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<MonsterDto> monsters) {
        this.monsters = monsters;
    }

    public List<ItemsDto> getItems() {
        return items;
    }

    public void setItems(List<ItemsDto> items) {
        this.items = items;
    }


    public  void setResponse(DungeonDto dungeonDto){
        monsters = dungeonDto.getMonsters();
        dungeonId = dungeonDto.getId();
        items = dungeonDto.getItems();


    }
}
