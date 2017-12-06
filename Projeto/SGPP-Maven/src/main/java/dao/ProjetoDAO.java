/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Projeto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author CristianoSilva
 */
public class ProjetoDAO extends BaseDAO<Projeto>{ 
    private static final long serialVersionUID = 5953225846505938118L;
    private EntityManager em;
    
    public List<Projeto> buscarPeloCoordenador(Long id) {
        em = JPAUtil.getEntityManager();
        TypedQuery<Projeto> query = em.createQuery(
                "SELECT p FROM Projeto p where p.coordenador.id = " + id,
                Projeto.class);
        
        try {
            List<Projeto> projetos = query.getResultList();
            em.close();
            return projetos;
        } 
        catch (Exception e) {
            em.close();
            return null;
        }
    }    
}
