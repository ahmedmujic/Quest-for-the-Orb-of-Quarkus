package ba.codecta.academy.repository.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "OrbofQuarkus", name = "MONSTER")
public class Monster extends ModelObject{
    public Monster(Double health, Double damage, String name) {
        this.health = health;
        this.damage = damage;
        this.name = name;
    }
    public Monster(){
        super();
    }
    @SequenceGenerator(
            name = "monsterSeq",
            schema = "OrbofQuarkus",
            allocationSize = 1,
            sequenceName = "MONSTER_SEQ"
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monsterSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name="HEALTH", nullable = false)
    private Double health = 100.0;

    @Column(name="DAMAGE",nullable = false)
    private Double damage = 100.0;

    @Column(name="NAME", nullable = false)
    private String name;

    @Type(type = "numeric_boolean")
    @Column(name = "ALIVE", nullable = false)
    private Boolean alive = true;


    @ManyToMany(mappedBy = "monsters")
    private List<Dungeon> dungeons = new ArrayList<>();

    @ManyToMany(mappedBy = "monstersItems")
    private List<Items> items = new ArrayList<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public Double getDamage() {
        return damage;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dungeon> getDungeons() {
        return dungeons;
    }

    public void setDungeons(List<Dungeon> dungeons) {
        this.dungeons = dungeons;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
