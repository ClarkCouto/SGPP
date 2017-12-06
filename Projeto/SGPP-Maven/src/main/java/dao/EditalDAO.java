/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Edital;
import entities.Projeto;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author CristianoSilva
 */
public class EditalDAO extends BaseDAO<Edital>{ 
    private static final long serialVersionUID = 5953225846505938118L;
    private EntityManager em;
    
    public boolean possuiProjetos(Long id) {
        em = JPAUtil.getEntityManager();
        TypedQuery<Projeto> query = em.createQuery(
                "SELECT p FROM Projeto p where p.edital.id = " + id,
                Projeto.class);
        
        try {
            Integer quantidade = query.getMaxResults();
            em.close();
            return (quantidade > 0 ? true : false);
        } 
        catch (Exception e) {
            em.close();
            return true;
        }
    }
    
}
