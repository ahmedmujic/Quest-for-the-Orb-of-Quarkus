package ba.codecta.academy.services.impl;

import ba.codecta.academy.repository.*;
import ba.codecta.academy.repository.entity.*;
import ba.codecta.academy.services.QoQService;
import ba.codecta.academy.services.model.*;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.print.attribute.standard.Destination;
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
    @Inject
    InventoryItemsRepository inventoryItemsRepository;
    private static ModelMapper modelMapper = new ModelMapper();

    @Override
    public NewGameDto createGame(PlayerDto player) {

        //generating items and weapons
        playerRepository.generateWeapons();
        itemsRepository.generateItems();

        ModelMapper modelMapper = new ModelMapper();

        Player playerEntity = modelMapper.map(player, Player.class);

        //create player inventory
        Inventory inventory = new Inventory();
        inventoryRepository.save(inventory);


        System.out.println("inventory id"+inventory.getId());
        //create players inital weapon
        Weapon weapon = new Weapon("Knife", 5.0, 10000);
        inventory.getWeapons().add(weapon);
        Weapon weapon2 = new Weapon("AK47", 50.0, 30);
        inventory.getWeapons().add(weapon2);
        weaponRepository.save(weapon2);
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
                Dungeon dungeon = new Dungeon();
                String dungeonName = faker.harryPotter().house();
                dungeon.setName(dungeonName);
                dungeon.setMap(map);

                dungeonRepository.save(dungeon);
                if (l == 0) {
                    playerEntity.setMap(map);
                    playerEntity.setCurrentDungeon(dungeon);
                }
                for (int i = 0; i < j+2; i++) {
                    String firstName = faker.witcher().monster();
                    Monster monster = new Monster(100.0, ((j + 1.0) * 3.0), firstName);
                    monster.getDungeons().add(dungeon);
                    List<Items> items = itemsRepository.getItemsPerNumber(1, 1);

                    monster.getItems().add(items.get(0));
                    monsterRepository.save(monster);
                    dungeon.getMonsters().add(monster);
                    dungeonList.add(dungeon);
                    dungeonRepository.save(dungeon);
                }
                int random = (int) (Math.random() * j+1 + 1);
                for (int k = 0; k < random; k++) {
                    if (k % 2 == 0) {
                        PowerUps powerUps = new PowerUps(faker.food().fruit(), 20.0 * (k + 5));
                        powerUps.getDungeons().add(dungeon);
                        itemsRepository.save(powerUps);
                    } else {
                        HealingPotion healingPotion = new HealingPotion( faker.medical().medicineName(), 20.0 * (k + 1));
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

            mapRepository.save(map);
        }
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(playerEntity.getId());
        playerDto.setCurrentDungeon(modelMapper.map(playerEntity.getCurrentDungeon(), DungeonDto.class));
        playerDto.setMap(modelMapper.map(playerEntity.getMap(), MapDto.class));
        playerDto.setPowerBoost(playerEntity.getPowerBoost());
        playerDto.setScore(playerDto.getScore());
        playerDto.setName(playerEntity.getName());
        playerDto.setHealth(player.getHealth());
        playerDto.setCurrentDungeon(player.getCurrentDungeon());




        NewGameDto newGameDto = new NewGameDto();
        newGameDto.setGameId(gameEntity.getId());

        List<ItemsDto> items = mapAll(inventoryItemsRepository.getItemsByInventoryId(playerEntity.getPlayerInventory().getId()), ItemsDto.class);
        System.out.println(items);
       InventoryDto inventoryDto  = new InventoryDto();
       inventoryDto.setItems(items);

       inventoryDto.setWeapons(mapAll(playerEntity.getPlayerInventory().getWeapons(), WeaponDto.class));
       inventoryDto.setId(playerEntity.getPlayerInventory().getId());

       playerDto.setPlayerInventory(inventoryDto);
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
        if(currentDungeon.getId().equals(10)) return  null;
        List<Monster> monsters = monsterRepository.getAliveMonstersByDungeon(currentDungeon);
        if(monsters.size() == 0){

            System.out.println(currentDungeon.getId());
            System.out.println("prvi uslov");
            currentDungeon = dungeonRepository.findById(currentDungeon.getId()+1);
            System.out.println(currentDungeon.getId());
            DungeonDto dungeonDto = new DungeonDto();
            dungeonDto.setMonsters(mapAll(currentDungeon.getMonsters(), MonsterDto.class));
            dungeonDto.setId(currentDungeon.getId());
            dungeonDto.setItems(mapAll(currentDungeon.getItems(), ItemsDto.class));
            game.getPlayer().setCurrentDungeon(currentDungeon);
            playerRepository.save(game.getPlayer());
            MovePlayerDto movePlayerDto = new MovePlayerDto();
            movePlayerDto.setResponse(dungeonDto);

            return movePlayerDto;
        }
        Map currenMap = game.getPlayer().getMap();
        if(currentDungeon.getFinished())
            System.out.println("drugi uslov");
            if(currenMap.getDungeons().size() == currentDungeon.getId()+1) {
                System.out.println("treci uslov");
                currenMap = mapRepository.findById(currenMap.getId() + 1);
                currentDungeon = dungeonRepository.findById(currentDungeon.getId() + 1);
                game.getPlayer().setCurrentDungeon(currentDungeon);
                mapRepository.save(currenMap);
                gameRepository.save(game);
                dungeonRepository.save(currentDungeon);
            }

        DungeonDto dungeonDto = new DungeonDto();
        dungeonDto.setMonsters(mapAll(currentDungeon.getMonsters(), MonsterDto.class));
        dungeonDto.setId(currentDungeon.getId());
        dungeonDto.setItems(mapAll(currentDungeon.getItems(), ItemsDto.class));
        MovePlayerDto movePlayerDto = new MovePlayerDto();
        movePlayerDto.setResponse(dungeonDto);
        return movePlayerDto;
    }

    @Override
    public FightResponseDto fightWithMonster(Integer id, AttackDto attackDto) {
        System.out.println("uslo");
        Double score = 0.0;

        Dungeon currentDungeon = gameRepository.currentDungeonId(id);
        Monster attackMonster = monsterRepository.findById(attackDto.getMonsterID());
        List<Monster> aliveMonsters = monsterRepository.getAliveMonstersByDungeon(currentDungeon);

       /* if (currentDungeon.getMonsters() == null || !aliveMonsters.contains(attackMonster)) {
            currentDungeon.setFinished(true);
            dungeonRepository.save(currentDungeon);
            return null;
        }*/


        Player currentPlayer = playerRepository.getCurrentPlayerByGameId(id);
        Weapon currentWeapon = weaponRepository.findById(attackDto.getWeaponID());
        currentPlayer.setWeapon(currentWeapon);


        Integer weaponHealth = currentPlayer.getWeapon().getWeaponHealth();

            while (currentPlayer.getHealth() > 0 && attackMonster.getHealth() > 0) {
                currentPlayer.setScore(currentPlayer.getScore() + attackMonster.getHealth());
                attackMonster.setHealth((attackMonster.getHealth() - currentPlayer.getWeapon().getDamage()*randomDmgForAttack())* currentPlayer.getPowerBoost());
                weaponHealth--;
                if(attackMonster.getHealth() > 0)
                    currentPlayer.setHealth(currentPlayer.getHealth() - attackMonster.getDamage()*randomDmgForAttack());

            }

        currentPlayer.setScore(Math.round(currentPlayer.getScore()*100d) / 100d);
        currentPlayer.setHealth(Math.round(currentPlayer.getHealth()*100d) / 100d);
        currentWeapon.setWeaponHealth(weaponHealth);
        weaponRepository.save(currentWeapon);
        FightResponseDto fightResponseDto = new FightResponseDto();

        if (currentPlayer.getHealth() > 0) {
            attackMonster.setAlive(false);
            inventoryItemsRepository.asignItems(currentPlayer.getPlayerInventory(), attackMonster.getItems());
            //currentPlayer.getPlayerInventory().getItems().addAll(attackMonster.getItems());
            fightResponseDto = new FightResponseDto(
                    "Good job you killed"+attackMonster.getName(),
                    Math.round(currentPlayer.getHealth()*100d) / 100d,
                    attackMonster.getName(),
                    modelMapper.map(currentPlayer.getWeapon(),WeaponDto.class),
                    mapAll(attackMonster.getItems(),ItemsDto.class),
                    true);


            attackMonster.setAlive(false);
            if(dungeonRepository.isDungeonEmpty(currentDungeon.getId()) == true) {
                currentDungeon.setFinished(true);
            }
            dungeonRepository.save(currentDungeon);
            monsterRepository.save(attackMonster);
            playerRepository.save(currentPlayer);
            System.out.println("finished");
            System.out.println(currentDungeon.getFinished());
        } else if (attackMonster.getHealth() > 0 && currentPlayer.getHealth() < 0) {
            fightResponseDto = new FightResponseDto(
                    attackMonster.getName()+"killed you.",
                    0.0,
                    null,
                    null,
                    null,
                    false);
        }
        if(currentDungeon.getId().equals(10) && currentPlayer.getHealth() > 0){
            System.out.println("kraj1");
            boolean finished = true;
            for(int i=0;i<currentDungeon.getMonsters().size();i++){
                Monster monster = currentDungeon.getMonsters().get(i);
                if(monster.getAlive()) finished = false;
            }
            if(finished == true){
                System.out.println("kraj2");
                fightResponseDto.setMessage("Good job you won Orb od Quarkus");
            }

        }
        return fightResponseDto;
    }

    @Override
    public PlayerDto getPlayerById(Integer id) {
        Player player = playerRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        PlayerDto playerDto = new PlayerDto();

        Inventory inventory = player.getPlayerInventory();
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setId(inventory.getId());
        inventoryDto.setWeapons(mapAll(player.getPlayerInventory().getWeapons(), WeaponDto.class));
        inventoryDto.setItems(mapAll(inventoryItemsRepository.getItemsByInventoryId(player.getPlayerInventory().getId()), ItemsDto.class));
        for(int i=0;i<inventoryDto.getItems().size();i++){
            inventoryDto.getItems().get(i).setQuantity(inventoryItemsRepository.getQuantityByItemId(inventoryDto.getItems().get(i).getId()));
        }

        playerDto.setPlayerInventory(inventoryDto);
        playerDto.setScore(player.getScore());
        playerDto.setWeapon(modelMapper.map(player.getWeapon(),WeaponDto.class));
        playerDto.setName(playerDto.getName());
        playerDto.setId(playerDto.getId());
        playerDto.setHealth(player.getHealth());
        playerDto.setPowerBoost(player.getPowerBoost());
        playerDto.setMap_Id(player.getCurrentDungeon().getMap().getId());
        playerDto.setDungeon_Id(player.getCurrentDungeon().getId());
        return  playerDto;
    }

    @Override
    public FleeResponseDto fleeDungeon(Integer id) {
        Player player = playerRepository.getCurrentPlayerByGameId(id);
        Dungeon dungeon = player.getCurrentDungeon();
        Double healthLose = 0.0;
        Double scoreLose = 0.0;
        for(int i=0;i<dungeon.getMonsters().size();i++){
            healthLose += dungeon.getMonsters().get(i).getDamage();
            scoreLose += dungeon.getMonsters().get(i).getHealth();
        }
        player.setHealth(player.getHealth()-healthLose);
        player.setScore(player.getScore()-scoreLose);
        Dungeon currentDungeon = dungeonRepository.findById(player.getCurrentDungeon().getId()+1);
        player.setCurrentDungeon(currentDungeon);
        playerRepository.save(player);

        InventoryDto inventory = new InventoryDto();
        inventory.setId(player.getId());
        inventory.setWeapons(mapAll(player.getPlayerInventory().getWeapons(), WeaponDto.class));
        inventory.setItems(mapAll(inventoryItemsRepository.getItemsByInventoryId(player.getPlayerInventory().getId()), ItemsDto.class));

        for(int i=0;i<inventory.getItems().size();i++){
            inventory.getItems().get(i).setQuantity(inventoryItemsRepository.getQuantityByItemId(inventory.getItems().get(i).getId()));
        }

        PlayerDto playerDto = new PlayerDto();
        playerDto.setPlayerInventory(inventory);
        playerDto.setWeapon(modelMapper.map(player.getWeapon(),WeaponDto.class));
        playerDto.setName(playerDto.getName());
        playerDto.setId(playerDto.getId());
        playerDto.setHealth(player.getHealth());
        playerDto.setScore(player.getScore());
        FleeResponseDto fleeResponseDto = new FleeResponseDto(
                dungeon.getId(),
                playerDto,
                scoreLose,
                healthLose

        );
        return fleeResponseDto;
    }

    @Override
    public List<ItemsDto> collectItems(Integer id) {
        Player player = playerRepository.getCurrentPlayerByGameId(id);
        Dungeon dungeon = player.getCurrentDungeon();
        if(dungeon.getFinished()){
           // player.getPlayerInventory().getItems().addAll(dungeon.getItems());
           // List<Items> items = inventoryItemsRepository.getItemsByInventoryId(player.getPlayerInventory().getId());
            List<Items> items1 = dungeon.getItems();
            inventoryItemsRepository.asignItems(player.getPlayerInventory(), items1);
            List<ItemsDto> itemsDtos = mapAll(items1,ItemsDto.class);
            return  itemsDtos;

        }
        else return null;

    }


    @Override
    public PlayerDto healPlayer(Integer playerId, Integer healId) {
        Player player = playerRepository.findById(playerId);


        List<Items> items = inventoryItemsRepository.getItemsByInventoryId(player.getPlayerInventory().getId());

        for(int i=0;i<items.size();i++){
            if(items.get(i).getId().equals(healId)){
                HealingPotion healingPotion = (HealingPotion) items.get(i);
                player.setHealth(player.getHealth()+healingPotion.getHealthAddition());
                boolean itemValidation = inventoryItemsRepository.removeItem(player.getPlayerInventory().getId(),items.get(i));
                if(itemValidation)
                playerRepository.save(player);
                else
                    return null;
            }
        }
        PlayerDto playerDto = new PlayerDto();
        playerDto.setName(player.getName());
        playerDto.setWeapon(modelMapper.map(player.getWeapon(),WeaponDto.class));
        playerDto.setName(playerDto.getName());
        playerDto.setId(playerDto.getId());
        playerDto.setHealth(player.getHealth());

        return playerDto;
    }

    @Override
    public PlayerDto powerUp(Integer playerId, Integer powerId) {
        System.out.println("USLO U POJACAVANJE");
        Player player = playerRepository.findById(playerId);
        PowerUps powerUps = (PowerUps) itemsRepository.findById(powerId);

        player.setPowerBoost(player.getPowerBoost() + (player.getPowerBoost()/powerUps.getPowerValue()));


        playerRepository.save(player);
        PlayerDto playerDto = new PlayerDto();
        playerDto.setName(player.getName());
        playerDto.setPowerBoost(player.getPowerBoost());
        playerDto.setId(playerDto.getId());

        return playerDto;
    }

    public Double randomDmgForAttack() {
        Double maxDmg = 6.0;
        Double minDmg = 1.0;
        return (Math.random() * maxDmg + minDmg) / 5.0;
    }
}
