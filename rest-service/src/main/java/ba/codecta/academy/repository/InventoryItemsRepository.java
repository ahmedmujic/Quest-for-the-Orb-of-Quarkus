package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class InventoryItemsRepository extends  Repository<InventoryItems, InventoryItemsPk>{

    public  InventoryItemsRepository(){
        super(InventoryItems.class);
    }

    public void asignItems(Inventory inventory, List<Items> items){
        for(int i=0;i<items.size();i++){
            InventoryItemsPk inventoryItemsPk = new InventoryItemsPk();
            Items item = items.get(i);
            inventoryItemsPk.setInventory(inventory.getId());

            inventoryItemsPk.setItems(item.getId());
            System.out.println(inventoryItemsPk.getItems());
            System.out.println(inventoryItemsPk.getInventory());
            InventoryItems inventoryItems = findById(inventoryItemsPk);
            if(inventoryItems != null){
                inventoryItems.setQuantity(inventoryItems.getQuantity()+1);
                save(inventoryItems);
            }
            else if (inventoryItems == null)
            {
                InventoryItems newInventoryItems = new InventoryItems();
                newInventoryItems.setItems(item);
                newInventoryItems.setInventory(inventory);
                newInventoryItems.setQuantity(1);
                add(newInventoryItems);
            }
        }
    }
    public boolean removeItem(Integer inventoryId,Items items){

        InventoryItemsPk id = new InventoryItemsPk();
        id.setInventory(inventoryId);
        id.setItems(items.getId());

        InventoryItems inventoryItems = findById(id);

       if(inventoryItems.getQuantity() == 0){
           return false;
       }
        inventoryItems.setQuantity(inventoryItems.getQuantity()-1);
        save(inventoryItems);
        return true;
    }
    public List<Items> getItemsByInventoryId(Integer id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Items> cq = cb.createQuery(Items.class);
        Root<Items> root = cq.from(Items.class);
        root.fetch("inventoryAsoc", JoinType.INNER);
        CriteriaQuery<Items> all = cq.select(root);

        all.where(cb.equal(root.join("inventoryAsoc").get("inventory"), id));

        TypedQuery<Items> allQuery = entityManager.createQuery(all);

        System.out.println(allQuery.getResultList());
        return allQuery.getResultList();
    }

    public Integer getQuantityByItemId(Integer id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InventoryItems> cq = cb.createQuery(InventoryItems.class);
        Root<InventoryItems> root = cq.from(InventoryItems.class);

        CriteriaQuery<InventoryItems> all = cq.select(root);

        all.where(cb.equal(root.get("items"), id));

        TypedQuery<InventoryItems> allQuery = entityManager.createQuery(all);
        return allQuery.getSingleResult().getQuantity();

    }
}
