/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Curso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author CristianoSilva
 */
public class CursoDAO extends BaseDAO<Curso>{ 
    private static final long serialVersionUID = 5953225846505938118L;
    private EntityManager em;
    
    public List<Curso> findByInstituicao(Long idInstituicao) {
        em = JPAUtil.getEntityManager();
        TypedQuery<Curso> query = em.createQuery(
                "SELECT c FROM Curso c "
                + "where c.instituicao_id = :idInstituicao",
                Curso.class);
        query.setParameter("idInstituicao", idInstituicao);
        
        try {
            List<Curso> cursos = query.getResultList();
            em.close();
            return cursos;
        } 
        catch (Exception e) {
            em.close();
        }
        return null;
    }
    
}
