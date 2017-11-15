/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.ColaboradorDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author CristianoSilva
 */
@Entity
public class Colaborador extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
    
    @Column(nullable=false, columnDefinition = "VARCHAR(50)")
    private String nome;

    public Colaborador() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nome);
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
        final Colaborador other = (Colaborador) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    } 
    
    public Colaborador buscarPeloId(Long id){
        return new ColaboradorDAO().findById(id);
    }
    
    public List<Colaborador> buscarTodos() {
        return new ColaboradorDAO().findAll();
    }
   
    public boolean remover(Long id) {
        return new ColaboradorDAO().remove(id);
    }  
    
    public boolean salvar(){
        return new ColaboradorDAO().save(this);
    }
}
