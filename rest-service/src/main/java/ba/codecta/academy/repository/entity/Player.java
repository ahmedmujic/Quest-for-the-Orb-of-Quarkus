package ba.codecta.academy.repository.entity;


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
    private Integer score = 0;

    @Column(name="HEALTH")
    private Integer health = 100;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private List<Game> games = new ArrayList<>();

    @ManyToOne
    private Map map;

    @ManyToOne
    private Dungeon currentDungeon;

    @Column(name = "HEALING_POINTS")
    @ColumnDefault(value="100")
    private Integer healingPoting = 100;


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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getHealingPoting() {
        return healingPoting;
    }

    public void setHealingPoting(Integer healingPoints) {
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
