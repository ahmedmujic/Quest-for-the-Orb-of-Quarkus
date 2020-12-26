package ba.codecta.academy.services;

import ba.codecta.academy.services.model.*;

public interface QoQService {

    PlayerDto addPlayer(PlayerDto player);
    NewGameDto createGame(PlayerDto player);
    MovePlayerDto movePlayer(Integer id);
    FightResponseDto fightWithMonster(Integer id, AttackDto attackDto);
}
