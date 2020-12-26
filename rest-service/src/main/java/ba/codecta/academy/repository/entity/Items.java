package ba.codecta.academy.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ITEMS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Items extends ModelObject{

    @SequenceGenerator(
            schema = "OrbofQuarkus",
            name = "itemsSeq",
            sequenceName = "ITEMS_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(generator = "itemsSeq", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToMany(mappedBy = "items")
    private List<Dungeon> dungeons = new ArrayList<>();

    @ManyToMany(mappedBy = "items")
    private List<Inventory> inventories = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            schema = "OrbofQuarkus",
            name = "ITEMS_MONSTER",
            joinColumns = @JoinColumn(name = "ITEMS_ID"),
            inverseJoinColumns = @JoinColumn(name = "MONSTER_ID")
    )
    private List<Monster> monstersItems = new ArrayList<>();

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public List<Monster> getMonstersItems() {
        return monstersItems;
    }

    public void setMonstersItems(List<Monster> monstersItems) {
        this.monstersItems = monstersItems;
    }

    public List<Dungeon> getDungeons() {
        return dungeons;
    }

    public void setDungeons(List<Dungeon> dungeons) {
        this.dungeons = dungeons;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
