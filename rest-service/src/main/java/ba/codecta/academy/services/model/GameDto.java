package ba.codecta.academy.services.model;

import ba.codecta.academy.repository.entity.Player;

public class GameDto {
    private Integer id;
    PlayerDto player;

    public GameDto(PlayerDto player) {

        this.player = player;
    }
    public GameDto(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDto player) {
        this.player = player;
    }

}
