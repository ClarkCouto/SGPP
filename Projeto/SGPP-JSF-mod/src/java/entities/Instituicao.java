/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.InstituicaoDAO;
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
public class Instituicao extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
    
    @Column(nullable=false, columnDefinition = "VARCHAR(50)")
    private String nome;

    public Instituicao() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.nome);
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
        final Instituicao other = (Instituicao) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    public Instituicao buscarPeloId(Long id){
        return new InstituicaoDAO().findById(id);
    }
    
    public List<Instituicao> buscarTodos() {
        return new InstituicaoDAO().findAll();
    }
   
    public boolean remover(Long id) {
        return new InstituicaoDAO().remove(id);
    }
    
    public boolean salvar(){
        return new InstituicaoDAO().save(this);
    }
}