/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import data.UserRepository;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import entities.Usuario;

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
        
        Boolean userAdded = UserRepository.getInstance().add(usuario);
        
        if (!userAdded) {
            System.out.println("Erro ao adicionar usuário.\n");
        }
        
        UserRepository.getInstance().list();
        
        return "login";
        
    }
    
    public void limpar () {
        this.setUsuario(new Usuario());
    }
    
    
}
