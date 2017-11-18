/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author CristianoSilva
 */
public class UsuarioDAO extends BaseDAO<Usuario>{ 
    private static final long serialVersionUID = 5953225846505938118L;
    private EntityManager em;
    
    public Usuario findByCpf(String cpf) {
        em = JPAUtil.getEntityManager();
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u "
                + "where u.cpf like '"
                + cpf + "'",
                Usuario.class);

        Usuario user = null;
        
        try {
            List<Usuario> usuario = query.getResultList();
            user = usuario.get(0);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        em.close();
        return user;
    }
}
