package ba.codecta.academy.services.model;

import ba.codecta.academy.repository.entity.InventoryItems;
import ba.codecta.academy.repository.entity.Items;
import ba.codecta.academy.repository.entity.Player;
import ba.codecta.academy.repository.entity.Weapon;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class InventoryDto {
    private Integer id;
    private List<ItemsDto> items;
    @JsonIgnore
    private List<PlayerDto> players;
    @JsonIgnore
    private List<InventoryItemsDto> itemsAsoc;
    private List<WeaponDto> weapons;


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

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public List<InventoryItemsDto> getItemsAsoc() {
        return itemsAsoc;
    }



    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

    public List<WeaponDto> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<WeaponDto> weapons) {
        this.weapons = weapons;
    }
}
