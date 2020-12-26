package ba.codecta.academy.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "WEAPON", schema = "OrbofQuarkus")
public class Weapon extends  ModelObject{

    public Weapon(String weaponName, Double damage, Integer weaponHealth ) {
        this.weaponName = weaponName;
        this.damage = damage;
        this.weaponHealth = weaponHealth;
    }
    Weapon(){

    }
    @SequenceGenerator(
            name = "weaponSeq",
            sequenceName = "WEAPON_SEQ",
            allocationSize = 1,
            schema = "OrbofQuarkus"
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weaponSeq")
    @Id
    @Column(name = "ID")
    private Integer id;


    @Column(name = "NAME")
    private String weaponName;

    @Column(name = "DAMAGE")
    private Double damage;

    @Column(name = "WEAPON_HEALTH")
    private Integer weaponHealth;
    @OneToMany(mappedBy = "weapon", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public Double getDamage() {
        return damage;
    }

    public Integer getWeaponHealth() {
        return weaponHealth;
    }

    public void setWeaponHealth(Integer weaponHealth) {
        this.weaponHealth = weaponHealth;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }
}
