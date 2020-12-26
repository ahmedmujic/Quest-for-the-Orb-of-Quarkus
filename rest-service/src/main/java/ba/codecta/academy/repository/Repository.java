package ba.codecta.academy.repository;

import ba.codecta.academy.repository.entity.ModelObject;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Repository <T extends ModelObject, PK extends Serializable> {
    private Class<T> entityClass;

    protected Repository(Class<T> entityClass){
        this.entityClass = entityClass;
    }
    @Inject
    EntityManager entityManager;

    public T add(T modelObject)
    {
        modelObject.setCreatedOn(LocalDateTime.now());
        modelObject.setModifiedOn(LocalDateTime.now());
        entityManager.persist(modelObject);
        return modelObject;
    }

    public List<T>  addList(List<T> list){

        list.forEach((n) -> {
            n.setCreatedOn(LocalDateTime.now());
            n.setModifiedOn(LocalDateTime.now());
            entityManager.persist(n);

        });
        return list;


    }


    public T findById(PK id)
    {
        T result = entityManager.find(this.entityClass, id);
        if(result != null) {
            return result;
        }
        return null;
    }

    public T save(T modelObject)
    {
        T result = null;
        PK id = (PK)modelObject.getId();
        if(id != null) {
            result = findById(id);
        }
        if(id == null || result!= null) {
            entityManager.persist(modelObject);
            return modelObject;
        }
        return null;
    }
}
