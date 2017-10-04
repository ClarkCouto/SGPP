/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



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
        
        
        
        if ((cpf.equals("034.527.460-11")) && (senha.equals("123456"))) {
            return "index?faces-redirect=true";
        }
        
        return "login";
    }
    
}
