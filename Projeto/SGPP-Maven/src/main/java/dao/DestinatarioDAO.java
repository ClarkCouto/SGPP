/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Destinatario;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author CristianoSilva
 */
public class DestinatarioDAO extends BaseDAO<Destinatario>{ 
    private static final long serialVersionUID = 5953225846505938118L;
    private EntityManager em;
    
    public Destinatario findByCpf(String cpf) {
        em = JPAUtil.getEntityManager();
        TypedQuery<Destinatario> query = em.createQuery(
                "SELECT d FROM Destinatario d "
                + "where d.cpf like '"
                + cpf + "'",
                Destinatario.class);
        
        try {
            Destinatario destinatario = query.getSingleResult();
            em.close();
            return destinatario;
        } 
        catch (Exception e) {
            em.close();
            e.printStackTrace();
        }
        return null;
    }
}
