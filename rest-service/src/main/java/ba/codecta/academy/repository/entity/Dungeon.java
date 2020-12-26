package ba.codecta.academy.repository.entity;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DUNGEON", schema = "OrbofQuarkus")
public class Dungeon extends ModelObject{

    @SequenceGenerator(
            name = "dungeonSequence",
            schema = "OrbofQuarkus",
            initialValue = 1,
            sequenceName = "DUNGEON_SEQ"
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dungeonSequence")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Type(type = "numeric_boolean")
    @Column(name = "FINISHED", nullable = false)
    private Boolean finished = false;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            schema = "OrbofQuarkus",
            name = "DUNGEON_ITEM",
            joinColumns = @JoinColumn(name = "DUNGEON_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEMS_ID")
    )
    private List<Items> items = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            schema = "OrbofQuarkus",
            name = "DUNGEON_MONSTER",
            joinColumns = @JoinColumn(name = "DUNGEOUN_ID"),
            inverseJoinColumns = @JoinColumn(name = "MONSTER_ID")
    )
    private List<Monster> monsters = new ArrayList<>();

    @ManyToOne
    private Map map;

    @OneToMany(mappedBy = "currentDungeon", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public Map getMap() {
        return map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
