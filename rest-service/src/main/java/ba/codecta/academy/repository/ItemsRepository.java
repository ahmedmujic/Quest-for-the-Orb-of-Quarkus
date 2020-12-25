package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.Items;
import ba.codecta.academy.repository.entity.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class ItemsRepository extends  Repository<Items, Integer>  {


    public ItemsRepository() {
        super(Items.class);
    }
}
