/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.TipoDocumentoDAO;
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
public class TipoDocumento extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
    
    @Column(nullable=false, columnDefinition = "VARCHAR(20)")
    private String nome;

    public TipoDocumento() {
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
        hash = 97 * hash + Objects.hashCode(this.nome);
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
        final TipoDocumento other = (TipoDocumento) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    public TipoDocumento buscarPeloId(Long id){
        return new TipoDocumentoDAO().findById(id);
    }
    
    public List<TipoDocumento> buscarTodos() {
        return new TipoDocumentoDAO().findAll();
    }
   
    public boolean remover(Long id) {
        return new TipoDocumentoDAO().remove(id);
    }  
    
    public boolean salvar(){
        return new TipoDocumentoDAO().save(this);
    }
}