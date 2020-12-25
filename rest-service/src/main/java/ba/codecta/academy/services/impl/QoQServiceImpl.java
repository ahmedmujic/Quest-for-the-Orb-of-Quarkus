package ba.codecta.academy.services.impl;

import ba.codecta.academy.repository.*;
import ba.codecta.academy.repository.entity.*;
import ba.codecta.academy.services.QoQService;
import ba.codecta.academy.services.model.*;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class QoQServiceImpl implements QoQService {
    @Inject
    PlayerRepository playerRepository;
    @Inject
    GameRepository gameRepository;
    @Inject
    MapRepository mapRepository;
    @Inject
    DungeonRepository dungeonRepository;
    @Inject
    MonsterRepository monsterRepository;
    @Inject
    ItemsRepository itemsRepository;
    @Inject
    WeaponRepository weaponRepository;

    @Override
    public PlayerDto addPlayer(PlayerDto player) {
        playerRepository.generateWeapons();
        ModelMapper modelMapper = new ModelMapper();
        Player playerEntity = modelMapper.map(player, Player.class);
        playerEntity = playerRepository.add(playerEntity);
        return modelMapper.map(playerEntity, PlayerDto.class);
    }

    @Override
    public GameDto createGame(PlayerDto player){

        playerRepository.generateWeapons();

        ModelMapper modelMapper = new ModelMapper();
        Player playerEntity = modelMapper.map(player, Player.class);
        playerEntity = playerRepository.add(playerEntity);
        PlayerDto playerDto = modelMapper.map(playerEntity, PlayerDto.class);

        GameDto game = new GameDto(playerDto);
        Game gameEntity = modelMapper.map(game, Game.class);
        gameEntity = gameRepository.add(gameEntity);


        int max;
        int min;

        Faker faker = new Faker();
        List<Dungeon> dungeonList = new ArrayList<>();
        //generating monsters with default health, damage and random name

        for(int l = 0;l<2;l++) {
            Map map = new Map();
            map.setName(faker.harryPotter().house());

            for (int j = 0; j < 5; j++) {
                max = j + 5;
                min = 0;
                int random = (int) (Math.random() * max + min);
                Dungeon dungeon = new Dungeon();
                String dungeonName = faker.harryPotter().house();
                dungeon.setName(dungeonName);
                for (int i = 0; i < random; i++) {
                    String firstName = faker.witcher().monster();
                    Monster monster = new Monster(100, ((j + 1) * 3), firstName);
                    monster.getDungeons().add(dungeon);
                    dungeon.getMonsters().add(monster);
                    monsterRepository.save(monster);
                    dungeonRepository.save(dungeon);
                }
                max = (max - random) + j;
                random = (int) (Math.random() * max + min);
                for (int k = 0; k < random; k++) {
                    if (k % 2 == 0) {
                        PowerUps powerUps = new PowerUps(faker.chuckNorris().fact(), 20.0 * (k + 5));
                        powerUps.getDungeons().add(dungeon);
                        itemsRepository.save(powerUps);
                    } else {
                        HealingPotion healingPotion = new HealingPotion(faker.food().dish(), 20.0 * (k + 1));
                        healingPotion.getDungeons().add(dungeon);
                        itemsRepository.save(healingPotion);
                        dungeon.getItems().add(healingPotion);
                    }


                }
                dungeon.setMap(map);
                dungeonRepository.save(dungeon);
                map.getDungeons().add(dungeon);
            }
            playerEntity.setCurrentDungeon(dungeonRepository.findById(1));
            playerRepository.save(playerEntity);
            map.getPlayers().add(playerEntity);

            mapRepository.add(map);
        }


        return modelMapper.map(gameEntity, GameDto.class);
    }

    @Override
    public DungeonDto movePlayer(Integer id) {
        ModelMapper modelMapper = new ModelMapper();
        Dungeon currentDungeon = gameRepository.currentDungeonId(id);

        return modelMapper.map(currentDungeon,DungeonDto.class);
    }

    @Override
    public DungeonDto fightWithMonster(Integer id, AttackDto attackDto) {
        Dungeon currentDungeon = gameRepository.currentDungeonId(id);
        if(currentDungeon.getMonsters() == null){
            return null;
        }
        Player currentPlayer = playerRepository.getCurrentPlayerByGameId(id);
        Monster attackMonster = monsterRepository.findById(attackDto.getMonsterID());
        Weapon playerWeapon = weaponRepository.findById(attackDto.getWeaponID());
        currentPlayer.setWeapon(playerWeapon);
        while(currentPlayer.getHealth() > 0 && attackMonster.getHealth() > 0){
           // Double damage = attackMonster.getHealth()  - (currentPlayer.getWeapon().getDamage() * Math.random()*);
            attackMonster.setHealth(attackMonster.getHealth()-(currentPlayer.getWeapon().getDamage()));
        }

        System.out.println(currentPlayer.getName());
        System.out.println(attackMonster.getName());
        return null;
    }
}
