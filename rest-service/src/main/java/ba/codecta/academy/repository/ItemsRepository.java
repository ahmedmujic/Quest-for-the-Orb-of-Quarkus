package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class ItemsRepository extends  Repository<Items, Integer>  {


    public ItemsRepository() {
        super(Items.class);
    }
    public Items getMonsterItem(){

        List<Items> items = new ArrayList<>();

        String query = "SELECT e FROM HealingPotion e ORDER BY random()";
        Query q = entityManager.createQuery(query);
        q.setMaxResults(1);
        HealingPotion healingItem = (HealingPotion) q.getSingleResult();
        items.add(healingItem);

        String query2 = "SELECT e FROM PowerUps e ORDER BY random()";
        Query q2 = entityManager.createQuery(query2);
        q2.setMaxResults(1);
        PowerUps powerItem = (PowerUps) q2.getSingleResult();
        items.add(powerItem);

        Random rand = new Random();
        return items.get(rand.nextInt(items.size()));

    }
    public List<Items> getItemsPerNumber(Integer maxHealthPotions, Integer maxPowerUps){

        List<Items> items = new ArrayList<>();

        String query = "SELECT e FROM HealingPotion e ORDER BY random()";
        Query q = entityManager.createQuery(query);
        q.setMaxResults(maxHealthPotions);
        items.addAll(q.getResultList());


        String query2 = "SELECT e FROM PowerUps e ORDER BY random()";
        Query q2 = entityManager.createQuery(query2);
        q2.setMaxResults(maxPowerUps);
        items.addAll(q2.getResultList());

        return items;

    }
    public void generateItems(){
        HealingPotion healingPotion = new HealingPotion("Potion of regeneration", 10.0);
        save(healingPotion);
        healingPotion = new HealingPotion("Elixir of Fire", 40.0);
        save(healingPotion);
        PowerUps powerUps = new PowerUps("Super Mushroom",20.0);
        save(powerUps);
    }
}
