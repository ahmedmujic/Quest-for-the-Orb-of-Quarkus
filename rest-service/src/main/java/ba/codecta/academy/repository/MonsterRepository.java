package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.Dungeon;
import ba.codecta.academy.repository.entity.Game;
import ba.codecta.academy.repository.entity.Monster;
import ba.codecta.academy.repository.entity.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class MonsterRepository extends  Repository<Monster, Integer>{

    public MonsterRepository(){
        super(Monster.class);
    }


    public Monster attackMonsterById(Integer id){
        return null;
    }

    public List<Monster> getAliveMonstersByDungeon(Dungeon dungeon){

       List<Monster> monsters = dungeon.getMonsters();

       List<Monster> aliveMonsters = new ArrayList<>();

       monsters.forEach(monster -> {
           if(monster.getAlive()){
               System.out.println(monster.getId());
               aliveMonsters.add(monster);
           }
       });

       return  aliveMonsters;
    }


}
