/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.Usuario;

/**
 *
 * @author mathe
 */

@ManagedBean
@RequestScoped
public class SignupBean {
    
    private Usuario usuario = new Usuario();
   
    
// Getters e Setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
// Ações
    
    public String cadastrar() {
        
        System.out.println(usuario);
        
        return "login";
        
    }
    
    public void limpar () {
        this.setUsuario(new Usuario());
    }
    
    
}
