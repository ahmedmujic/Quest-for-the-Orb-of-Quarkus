package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.Dungeon;
import ba.codecta.academy.repository.entity.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class DungeonRepository extends  Repository<Dungeon, Integer>  {

    public  DungeonRepository(){
        super(Dungeon.class);
    }
}
