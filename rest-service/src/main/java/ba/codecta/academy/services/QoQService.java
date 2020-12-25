package ba.codecta.academy.services;

import ba.codecta.academy.services.model.AttackDto;
import ba.codecta.academy.services.model.DungeonDto;
import ba.codecta.academy.services.model.GameDto;
import ba.codecta.academy.services.model.PlayerDto;

public interface QoQService {

    PlayerDto addPlayer(PlayerDto player);
    GameDto createGame(PlayerDto player);
    DungeonDto movePlayer(Integer id);
    DungeonDto fightWithMonster(Integer id, AttackDto attackDto);
}
