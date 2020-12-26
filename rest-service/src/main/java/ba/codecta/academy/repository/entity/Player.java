package ba.codecta.academy.repository.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "OrbofQuarkus", name = "PLAYER")
public class Player extends ModelObject{
    @SequenceGenerator(
            name = "playerSeq",
            sequenceName = "PLAYER_SEQ",
            schema = "OrbofQuarkus",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerSeq")
    @Id
    @Column(name="ID", nullable = false)
    private Integer id;


    @Column(name = "NAME", nullable = false)
    private String name;


    @ManyToOne()
    private Weapon weapon;

    @Column(name = "SCORE", nullable = false)
    private Double score = 0.0;

    @Column(name="HEALTH")
    private Double health = 1000.0;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<Game> games = new ArrayList<>();

    @ManyToOne
    private Map map;

    @ManyToOne
    private Dungeon currentDungeon;

    @ManyToOne
    private Inventory playerInventory;

    @Column(name = "HEALING_POTING")
    private Double healingPoting = 100.0;


    public Dungeon getCurrentDungeon() {
        return currentDungeon;
    }

    public void setCurrentDungeon(Dungeon currentDungeon) {
        this.currentDungeon = currentDungeon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(Inventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public Double getHealingPoting() {
        return healingPoting;
    }

    public void setHealingPoting(Double healingPoints) {
        this.healingPoting = healingPoints;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
