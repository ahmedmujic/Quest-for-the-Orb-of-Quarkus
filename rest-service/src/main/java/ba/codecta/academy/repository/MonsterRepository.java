package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.Dungeon;
import ba.codecta.academy.repository.entity.Game;
import ba.codecta.academy.repository.entity.Monster;

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
}
