/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import data.UserRepository;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.Usuario;



/**
 *
 * @author mathe
 */

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {    
    
    private String cpf;
    private String senha;
    
// Getters e Setters

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
// Ações

    public String logar() {
        
        Usuario user = UserRepository.getInstance().findByCpf(cpf);
        
        if (user == null || !user.checkSenha(senha)) {
                return "login?wrong-credentials=true";
        }        
        
        return "menu?faces-redirect=true";
    }
    
}
