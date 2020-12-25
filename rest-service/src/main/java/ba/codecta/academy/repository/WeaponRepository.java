package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.Player;
import ba.codecta.academy.repository.entity.Weapon;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class WeaponRepository  extends  Repository<Weapon, Integer>{
    public  WeaponRepository(){
        super(Weapon.class);
    }
}
