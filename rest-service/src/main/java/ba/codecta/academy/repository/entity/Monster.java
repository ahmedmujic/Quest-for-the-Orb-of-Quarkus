package ba.codecta.academy.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "OrbofQuarkus", name = "MONSTER")
public class Monster extends ModelObject{
    public Monster(Integer health, Integer damage, String name) {
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
    private Integer health = 100;

    @Column(name="DAMAGE",nullable = false)
    private Integer damage = 100;

    @Column(name="NAME", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "monsters")
    private List<Dungeon> dungeons = new ArrayList<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
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
