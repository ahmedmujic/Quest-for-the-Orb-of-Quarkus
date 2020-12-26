package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.Inventory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class InventoryRepository extends Repository <Inventory,Integer> {

    public InventoryRepository() {
        super(Inventory.class);
    }

}
