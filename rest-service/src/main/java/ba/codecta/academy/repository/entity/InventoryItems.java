package ba.codecta.academy.repository.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "InventoryItems")
@IdClass(InventoryItemsPk.class)
public class InventoryItems extends ModelObject implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "INVENTORY_ID", referencedColumnName = "id")
    private Inventory inventory;

    @Id
    @ManyToOne
    @JoinColumn(name = "ITEMS_ID", referencedColumnName = "id")
    private Items items;

    @Column(name = "quantity")
    private Integer quantity;

    @Override
    public InventoryItemsPk getId() {
        InventoryItemsPk inventoryItemsPk = new InventoryItemsPk();
        inventoryItemsPk.setInventory(inventory.getId());
        inventoryItemsPk.setItems(items.getId());
        return inventoryItemsPk;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }
}
