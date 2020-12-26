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

    @ManyToMany
    @JoinTable(
            schema = "OrbofQuarkus",
            name = "INVENTORY_ITEM",
            joinColumns = @JoinColumn(name = "INVENTORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEMS_ID")
    )
    private List<Items> items = new ArrayList<>();

    @OneToMany(mappedBy = "playerInventory", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public Integer getId() {
        return null;
    }
}
