package dao;

import entities.EntityInterface;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public abstract class BaseDAO<T extends EntityInterface> implements Serializable {

    private static final long serialVersionUID = -5953225846505938118L;
    private EntityManager em;
    private Class entidade;

    public BaseDAO() {
    }

    public Class getEntidade() {
        if (entidade == null) {
            entidade = (Class) ((ParameterizedType) getClass()
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

    public void remove(Long id) {
        em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            Object ref = em.getReference(getEntidade(), id);
            em.remove(ref);
            em.getTransaction().commit();
        } catch (EntityNotFoundException e) {
            System.out.println("Não existe o id: " + id);
        } finally {
            em.close();
        }
    }

    public void save(T t) {
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
        } catch(Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<T> findAll() {
        em = JPAUtil.getEntityManager();
        return em.createQuery("Select entity FROM " + getEntidade().getSimpleName() + " entity").getResultList();
    }
}