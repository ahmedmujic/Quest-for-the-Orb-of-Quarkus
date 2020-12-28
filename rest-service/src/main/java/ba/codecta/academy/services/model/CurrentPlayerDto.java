package ba.codecta.academy.services.model;

import ba.codecta.academy.repository.entity.Inventory;

public class CurrentPlayerDto {
    private Integer id;
    private String name;
    private InventoryDto inventory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InventoryDto getInventory() {
        return inventory;
    }

    public void setInventory(InventoryDto inventory) {
        this.inventory = inventory;
    }
}
