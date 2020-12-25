package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.Dungeon;
import ba.codecta.academy.repository.entity.Game;
import ba.codecta.academy.repository.entity.Player;
import ba.codecta.academy.repository.entity.Weapon;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class PlayerRepository extends  Repository<Player, Integer> {

    public  PlayerRepository(){
        super(Player.class);
    }

    public void generateWeapons(){

        Weapon weapon1 = new Weapon("Mač", 7,8);
        Weapon weapon2 = new Weapon("Strijela", 5,15);
        ArrayList<Weapon> weapons = new ArrayList<Weapon>(Arrays.asList(weapon1,weapon2));

        weapons.forEach((weapon)->{
            entityManager.persist(weapon);
        });
    }

    public void moveToNextDungeon(Integer id){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> query = builder.createQuery(Player.class);
        Root<Player> fromDungeon = query.from(Player.class);

        Join<Player, Game> details = fromDungeon.join("games", JoinType.INNER);

        List<Predicate> conditions = new ArrayList<>();
        conditions.add(builder.equal(fromDungeon.get("id"),details.get("id")));

        TypedQuery<Player> typedQuery = entityManager.createQuery(query
                .select(fromDungeon)
                .where(conditions.toArray(new Predicate[] {}))
                .distinct(true)
        );

    }



    public Player getCurrentPlayerByGameId(Integer id){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> query = builder.createQuery(Game.class);
        Root<Game> rootGame = query.from(Game.class);
        CriteriaQuery<Game> all = query.select(rootGame);

        List<Predicate> conditions = new ArrayList<>();
            conditions.add(builder.equal(rootGame.get("id"), id));

        all.where(conditions.toArray(new Predicate[] {}));

        TypedQuery<Game> allQuery = entityManager.createQuery(all);

        Game currentGame = allQuery.getSingleResult();

        Player currentPlayer = currentGame.getPlayer();

        return currentPlayer;
    }
}
