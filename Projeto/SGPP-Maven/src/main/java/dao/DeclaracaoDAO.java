/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Declaracao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author CristianoSilva
 */
public class DeclaracaoDAO extends BaseDAO<Declaracao>{ 
    private static final long serialVersionUID = 5953225846505938118L;
    private EntityManager em;
    
    public List<Declaracao> buscarPeloResponsavel(Long id) {
        em = JPAUtil.getEntityManager();
        TypedQuery<Declaracao> query = em.createQuery(
                "SELECT d FROM Declaracao d where d.responsavel.id = " + id,
                Declaracao.class);
        
        try {
            List<Declaracao> declaracoes = query.getResultList();
            em.close();
            return declaracoes;
        } 
        catch (Exception e) {
            em.close();
            return null;
        }
    }
}
