/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.LinkedList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import models.Usuario;

/**
 *
 * @author mathe
 */

@ManagedBean
@ApplicationScoped
public class RepoBean {
    private final LinkedList<Usuario> usuarios = new LinkedList<>();
    
// Getters

    public LinkedList<Usuario> getUsuarios() {
        return usuarios;
    }

    
    
    
}
