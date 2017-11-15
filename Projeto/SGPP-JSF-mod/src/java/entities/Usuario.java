/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.UsuarioDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CristianoSilva
 */
@Entity
@DiscriminatorValue(value="Usuario")
public class Usuario extends Pessoa implements Serializable {
    private static final long serialVersionUID = 5953225846505938118L;
            
    @Column(nullable=false, columnDefinition = "VARCHAR(30)")
    private String senha;
    
    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcesso;

    public Usuario() {
    }  

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.senha);
        hash = 67 * hash + Objects.hashCode(this.ultimoAcesso);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.ultimoAcesso, other.ultimoAcesso)) {
            return false;
        }
        return true;
    }
    
// Outros métodos
    public Boolean checkSenha(String s) {
        System.out.println("checkSenha Senha: " + s);
        return this.senha.equals(s);
    }
    
    public Boolean changeSenha(String newPass, String oldPass) {
        if (Objects.equals(this.senha, oldPass)) {
           this.senha = newPass; 
           return true;
        }        
        return false;
    }
}