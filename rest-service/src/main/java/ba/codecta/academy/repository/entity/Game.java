package ba.codecta.academy.repository.entity;

import javax.persistence.*;

@Entity
@Table(name="GAME", schema = "OrbofQuarkus")
public class Game extends ModelObject<Integer> {
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

    @ManyToOne
    private Player player;

    @Override
    public Integer getId() {
        return id;
    }

    public Game() {

    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }


    public void setPlayer(Player player) {
        this.player = player;
    }
}
