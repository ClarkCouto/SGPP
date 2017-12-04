/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Edital;
import entities.Lembrete;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author CristianoSilva
 */
public class LembreteDAO extends BaseDAO<Lembrete>{ 
    private static final long serialVersionUID = 5953225846505938118L;
    private EntityManager em;
    
    public List<Lembrete> buscarLembretesDetachedAtravesDoEdital(Long id) {
        em = JPAUtil.getEntityManager();
        Edital edital = new EditalDAO().findById(id);
        List<Lembrete> lembretes = null;    
        try {
            lembretes = edital.getLembretes();
            em.detach(lembretes);
        } 
        catch (Exception e) {
            em.close();
        }
        return lembretes;
    }
    
}
