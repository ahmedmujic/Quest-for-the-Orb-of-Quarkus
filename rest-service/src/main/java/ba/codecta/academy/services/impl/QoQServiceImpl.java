package ba.codecta.academy.services.impl;

import ba.codecta.academy.repository.*;
import ba.codecta.academy.repository.entity.*;
import ba.codecta.academy.services.QoQService;
import ba.codecta.academy.services.model.*;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    @Inject
    InventoryRepository inventoryRepository;
    private static ModelMapper modelMapper = new ModelMapper();

    @Override
    public PlayerDto addPlayer(PlayerDto player) {
        playerRepository.generateWeapons();
        ModelMapper modelMapper = new ModelMapper();
        Player playerEntity = modelMapper.map(player, Player.class);
        playerEntity = playerRepository.add(playerEntity);
        return modelMapper.map(playerEntity, PlayerDto.class);
    }

    @Override
    public NewGameDto createGame(PlayerDto player) {

        //generating items and weapons
        playerRepository.generateWeapons();
        itemsRepository.generateItems();

        ModelMapper modelMapper = new ModelMapper();

        Player playerEntity = modelMapper.map(player, Player.class);

        //create player inventory
        Inventory inventory = new Inventory();
        inventory.setItems(itemsRepository.getItemsPerNumber(1, 1));
        inventoryRepository.save(inventory);

        //create players inital weapon
        Weapon weapon = new Weapon("Knife", 5.0, 10000);
        weaponRepository.save(weapon);

        //asign weapon and inventory to player
        playerEntity.setWeapon(weapon);
        playerEntity.setPlayerInventory(inventory);

        //save player
        playerEntity = playerRepository.save(playerEntity);


        Game gameEntity = new Game();
        gameEntity.setPlayer(playerEntity);
        gameEntity = gameRepository.add(gameEntity);


        int max;
        int min;

        Faker faker = new Faker();
        List<Dungeon> dungeonList = new ArrayList<>();
        //generating monsters with default health, damage and random name

        for (int l = 0; l < 2; l++) {
            Map map = new Map();
            map.setName(faker.harryPotter().house());

            mapRepository.save(map);
            for (int j = 0; j < 5; j++) {
                max = j + 5;
                min = 0;
                int random = (int) (Math.random() * max + min);
                Dungeon dungeon = new Dungeon();
                String dungeonName = faker.harryPotter().house();
                dungeon.setName(dungeonName);
                dungeon.setMap(map);

                dungeonRepository.save(dungeon);
                if (l == 0) {
                    playerEntity.setMap(map);
                    playerEntity.setCurrentDungeon(dungeon);
                }
                for (int i = 0; i < random; i++) {
                    String firstName = faker.witcher().monster();
                    Monster monster = new Monster(100.0, ((j + 1.0) * 3.0), firstName);
                    monster.getDungeons().add(dungeon);
                    monster.getItems().addAll(itemsRepository.getItemsPerNumber(1, 1));
                    monsterRepository.save(monster);
                    dungeon.getMonsters().add(monster);
                    dungeonList.add(dungeon);
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
            playerEntity.getPlayerInventory().getItems().add(itemsRepository.getMonsterItem());
            playerEntity.setCurrentDungeon(dungeonRepository.findById(1));
            playerRepository.save(playerEntity);
            map.getPlayers().add(playerEntity);

            mapRepository.save(map);
        }
        PlayerDto playerDto = modelMapper.map(playerEntity, PlayerDto.class);
         NewGameDto newGameDto = new NewGameDto();
         newGameDto.setGameId(gameEntity.getId());
         newGameDto.setPlayer(playerDto);



        return newGameDto;
    }
    //array mapper
    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> modelMapper.map(entity, outCLass))
                .collect(Collectors.toList());
    }
    @Override
    public MovePlayerDto movePlayer(Integer id) {
        ModelMapper modelMapper = new ModelMapper();
        Game game = gameRepository.findById(id);
        Dungeon currentDungeon = gameRepository.currentDungeonId(id);
        Map currenMap = game.getPlayer().getMap();
        if(currentDungeon.getFinished())
            if(currenMap.getDungeons().size() == currentDungeon.getId()+1) {
                currenMap = mapRepository.findById(currenMap.getId() + 1);
                currentDungeon = dungeonRepository.findById(currentDungeon.getId() + 1);
                game.getPlayer().setCurrentDungeon(currentDungeon);
                mapRepository.save(currenMap);
                gameRepository.save(game);
                dungeonRepository.save(currentDungeon);
            }
        currentDungeon = dungeonRepository.findById(currentDungeon.getId()+1);


        DungeonDto dungeonDto = modelMapper.map(currentDungeon, DungeonDto.class);
        MovePlayerDto movePlayerDto = new MovePlayerDto();
        movePlayerDto.setResponse(dungeonDto);

        return movePlayerDto;
    }

    @Override
    public FightResponseDto fightWithMonster(Integer id, AttackDto attackDto) {
        Double score = 0.0;

        Dungeon currentDungeon = gameRepository.currentDungeonId(id);
        if (currentDungeon.getMonsters() == null) {
            return null;
        }
        Player currentPlayer = playerRepository.getCurrentPlayerByGameId(id);
        Monster attackMonster = monsterRepository.findById(attackDto.getMonsterID());
        Weapon currentWeapon = weaponRepository.findById(attackDto.getWeaponID());
        currentPlayer.setWeapon(currentWeapon);


        Integer weaponHealth = currentPlayer.getWeapon().getWeaponHealth();
        while (currentPlayer.getHealth() > 0 && attackMonster.getHealth() > 0) {
            attackMonster.setHealth(attackMonster.getHealth() - currentPlayer.getWeapon().getDamage()*randomDmgForAttack());
            weaponHealth--;
            if(attackMonster.getHealth() > 0)
            currentPlayer.setHealth(currentPlayer.getHealth() - attackMonster.getDamage()*randomDmgForAttack());
        }
        currentWeapon.setWeaponHealth(weaponHealth);
        weaponRepository.save(currentWeapon);
        FightResponseDto fightResponseDto = new FightResponseDto();
        if (currentPlayer.getHealth() > 0) {
            attackMonster.setAlive(false);
            currentPlayer.getPlayerInventory().getItems().addAll(attackMonster.getItems());
            fightResponseDto = new FightResponseDto(
                    "Good job you killed"+attackMonster.getName(),
                    Math.round(currentPlayer.getHealth()*100d) / 100d,
                    attackMonster.getName(),
                    modelMapper.map(currentPlayer.getWeapon(),WeaponDto.class),
                    mapAll(attackMonster.getItems(),ItemsDto.class),
                    true);

            score = score + currentPlayer.getScore() + attackMonster.getHealth();
            currentPlayer.setScore(score);
            attackMonster.setAlive(false);
            monsterRepository.save(attackMonster);
            playerRepository.save(currentPlayer);
        } else if (attackMonster.getHealth() > 0 && currentPlayer.getHealth() < 0) {
            fightResponseDto = new FightResponseDto(
                    attackMonster.getName()+"killed you.",
                    0.0,
                    null,
                    null,
                    null,
                    false);
        }

        return fightResponseDto;
    }

    public Double randomDmgForAttack() {
        Double maxDmg = 6.0;
        Double minDmg = 1.0;
        return (Math.random() * maxDmg + minDmg) / 5.0;
    }
}
