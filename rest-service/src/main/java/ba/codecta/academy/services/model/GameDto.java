package ba.codecta.academy.services.model;

import ba.codecta.academy.repository.entity.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

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


    public  String message(){
        return "Wellcome to new game";
    }
}
