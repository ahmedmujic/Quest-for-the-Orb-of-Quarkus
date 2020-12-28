package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.Dungeon;
import ba.codecta.academy.repository.entity.Monster;
import ba.codecta.academy.repository.entity.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class DungeonRepository extends  Repository<Dungeon, Integer>  {

    public  DungeonRepository(){
        super(Dungeon.class);
    }

    public  boolean isDungeonEmpty(Integer dungeonId){
        Dungeon monsters = findById(dungeonId);
        List<Monster> monsterList = monsters.getMonsters();
        for(int i=0;i<monsterList.size();i++){
            if(monsterList.get(i).getAlive() == true){
                return false;
            }
        }
        return true;
    }
}
