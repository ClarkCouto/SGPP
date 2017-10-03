/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.RequestScoped;
import models.Usuario;



/**
 *
 * @author mathe
 */

@ManagedBean(name="loginBean")
@RequestScoped
public class loginBean implements Serializable {
    private static final long serialVersionUID = 356240640918386194L;
    private Usuario usuarioLogado = new Usuario();
    
    // Getters e Setters

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    // Actions

    public String logar() {
        if (!(usuarioLogado.getCpf().equals("00000000000"))) {
            return "index";
        }
        if (!(usuarioLogado.getSenha().equals("1234"))) {
            return "index";
        }
        
        return "dashboard";
    }
    
}
