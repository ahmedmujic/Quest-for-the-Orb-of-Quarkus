package ba.codecta.academy.services.model;

import ba.codecta.academy.repository.entity.Inventory;
import ba.codecta.academy.repository.entity.InventoryItemsPk;
import ba.codecta.academy.repository.entity.Items;

public class InventoryItemsDto {
    private InventoryItemsPk id;
    private Items items;
    private Inventory inventory;
    private Integer quantity;

    public InventoryItemsPk getId() {
        return id;
    }


    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
