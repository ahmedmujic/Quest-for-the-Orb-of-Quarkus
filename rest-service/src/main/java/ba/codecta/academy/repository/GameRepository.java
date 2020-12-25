package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.Dungeon;
import ba.codecta.academy.repository.entity.Game;
import ba.codecta.academy.repository.entity.Map;
import ba.codecta.academy.repository.entity.Player;
import org.hibernate.Criteria;
import org.hibernate.sql.Select;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class GameRepository extends  Repository<Game, Integer> {

    public  GameRepository(){
        super(Game.class);
    }

    public Dungeon currentDungeonId(Integer id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> cq = cb.createQuery(Game.class);

        Root<Game> root = cq.from(Game.class);
        CriteriaQuery<Game> all = cq.select(root);

        List<Predicate> conditions = new ArrayList<>();
        conditions.add(cb.equal(root.get("id"), id));

        all.where(conditions.toArray(new Predicate[] {}));
        TypedQuery<Game> allQuery = entityManager.createQuery(all);

        Game currentGame = allQuery.getSingleResult();
        Dungeon currentDungeon = currentGame.getPlayer().getCurrentDungeon();
        currentDungeon.setMonsters(currentDungeon.getMonsters());

        return currentDungeon;


    }

}
