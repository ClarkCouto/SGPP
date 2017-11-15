/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.CategoriaBolsaDAO;
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
public class CategoriaBolsa extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
   
    
    @Column(nullable=false, columnDefinition = "VARCHAR(100)")
    private String descricao;
    
    @Column(nullable=false)
    private float valor;

    public CategoriaBolsa() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.descricao);
        hash = 47 * hash + Float.floatToIntBits(this.valor);
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
        final CategoriaBolsa other = (CategoriaBolsa) obj;
        if (Float.floatToIntBits(this.valor) != Float.floatToIntBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }   
    
    public CategoriaBolsa buscarPeloId(Long id){
        return new CategoriaBolsaDAO().findById(id);
    }
    
    public List<CategoriaBolsa> buscarTodos() {
        return new CategoriaBolsaDAO().findAll();
    }
   
    public boolean remover(Long id) {
        return new CategoriaBolsaDAO().remove(id);
    }
    
    public boolean salvar(){
        return new CategoriaBolsaDAO().save(this);
    }
}
