package ba.codecta.academy.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "OrbofQuarkus", name = "INVENTORY")
public class Inventory extends ModelObject {
    @SequenceGenerator(
            name = "inventorySeq",
            sequenceName = "INVENTORY_SEQ",
            schema = "OrbofQuarkus",
            allocationSize = 1)

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventorySeq")
    @Id
    @Column(name="ID", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "inventory")
    private List<InventoryItems> itemsAsoc;

    @ManyToMany
    @JoinTable(
            schema = "OrbofQuarkus",
            name = "INVENTORY_WEAPON",
            joinColumns = @JoinColumn(name = "INVENTORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "WEAPON_ID")
    )
    private List<Weapon> weapons = new ArrayList<>();

    @OneToMany(mappedBy = "playerInventory", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public List<InventoryItems> getItemsAsoc() {
        return itemsAsoc;
    }

    public void setItemsAsoc(List<InventoryItems> itemsAsoc) {
        this.itemsAsoc = itemsAsoc;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    @Override
    public Integer getId() {
        return id;
    }

}
