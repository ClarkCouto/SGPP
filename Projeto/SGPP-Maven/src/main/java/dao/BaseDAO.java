package dao;

import entities.BaseEntityAudit;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public abstract class BaseDAO<T extends BaseEntityAudit> implements Serializable {
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

    public List<T> findAll() {
        em = JPAUtil.getEntityManager();
        return (List) em.createQuery("Select entity FROM " + getEntidade().getSimpleName() + " entity").getResultList();
    }

    public T findById(Long id) {
        em = JPAUtil.getEntityManager();
        return (T) em.find(getEntidade(), id);
    }

    public boolean remove(Long id){
        em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            T t = findById(id);
            em.close();
            t.setAtivo(Boolean.FALSE);
            save(t);
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
}
