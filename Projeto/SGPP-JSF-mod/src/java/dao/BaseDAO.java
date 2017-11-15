package dao;

import entities.BaseEntity;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public abstract class BaseDAO<T extends BaseEntity> implements Serializable {
    private static final long serialVersionUID = -5953225846505938118L;
    private EntityManager em;
    private Class entidade;

    public Class getEntidade() {
        if (entidade == null) {
            entidade = (Class<T>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entidade;
    }

    public void setEntidade(Class entidade) {
        this.entidade = entidade;
    }

    public T findById(Long id) {
        em = JPAUtil.getEntityManager();
        return (T) em.find(getEntidade(), id);
    }

    public boolean remove(Long id){
        em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            Object obj = em.getReference(getEntidade(), id);
            em.remove(obj);
            em.getTransaction().commit();
            em.close();
            return true;
        } 
        catch (EntityNotFoundException e) {
            em.getTransaction().rollback();
            em.close();
            return false;
        } 
        catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            return false;
        } 
    }

    public boolean save(T t){
        em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        
        try {            
            T foundItem = null;
            if (t.getId() != null)
                foundItem = em.find((Class<T>) t.getClass(), t.getId());
            if (foundItem != null) {
                em.merge(t);
            } else {                
                em.persist(t);                
            }            
            em.getTransaction().commit();
            em.clear();
            em.close();
            return true;
        } 
        catch (Exception e) {
            em.getTransaction().rollback();
            em.clear();
            em.close();
            return false;
        }
    }

    public List<T> findAll() {
        em = JPAUtil.getEntityManager();
        return (List) em.createQuery("Select entity FROM " + getEntidade().getSimpleName() + " entity").getResultList();
    }
}
