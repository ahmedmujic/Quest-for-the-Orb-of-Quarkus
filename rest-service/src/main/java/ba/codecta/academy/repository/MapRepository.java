package ba.codecta.academy.repository;



import ba.codecta.academy.repository.entity.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class MapRepository extends  Repository<Map, Integer>{

    public  MapRepository(){
        super(Map.class);
    }
}
