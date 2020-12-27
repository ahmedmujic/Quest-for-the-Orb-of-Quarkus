package ba.codecta.academy.services;

import ba.codecta.academy.services.model.*;

import java.util.List;

public interface QoQService {

    PlayerDto addPlayer(PlayerDto player);
    NewGameDto createGame(PlayerDto player);
    MovePlayerDto movePlayer(Integer id);
    FightResponseDto fightWithMonster(Integer id, AttackDto attackDto);
    FleeResponseDto fleeDungeon(Integer id);
    PlayerDto getPlayerById(Integer id);
    List<ItemsDto> collectItems(Integer id);
    WeaponDto choosePlayerWeapon(Integer id, Integer weaponId);
}
