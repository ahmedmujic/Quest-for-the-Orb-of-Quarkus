package ba.codecta.academy.services.model;

import ba.codecta.academy.repository.entity.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class InventoryDto {
    private Integer id;
    private List<ItemsDto> items;
    @JsonIgnore
    private List<Player> players;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItemsDto> getItems() {
        return items;
    }

    public void setItems(List<ItemsDto> items) {
        this.items = items;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
