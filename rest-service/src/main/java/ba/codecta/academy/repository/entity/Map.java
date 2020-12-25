package ba.codecta.academy.repository.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MAP", schema = "OrbofQuarkus")
public class Map extends ModelObject {
    @SequenceGenerator(
            name = "gameSequence",
            allocationSize = 1,
            schema = "OrbofQuarkus",
            sequenceName = "GAME_SEQ"
    )

    @GeneratedValue(generator = "gameSequence", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "map", fetch = FetchType.LAZY)
    private List<Dungeon> dungeons = new ArrayList<>();

    @OneToMany(mappedBy = "map", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    @Column(name = "NAME", nullable = false)
    private String name;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Dungeon> getDungeons() {
        return dungeons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDungeons(List<Dungeon> dungeons) {
        this.dungeons = dungeons;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
