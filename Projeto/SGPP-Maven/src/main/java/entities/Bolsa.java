/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.BolsaDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author CristianoSilva
 */
@Entity
public class Bolsa extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
    
    @ManyToOne(cascade={CascadeType.MERGE}, fetch = FetchType.EAGER)
    private CategoriaBolsa categoriaBolsa;
    
    @Column(nullable=false, columnDefinition = "VARCHAR(50)")
    private String nome;
    
    @Column(nullable=false)
    private Integer quantidade; 

    public Bolsa() {
    }

    public CategoriaBolsa getCategoriaBolsa() {
        return categoriaBolsa;
    }

    public void setCategoriaBolsa(CategoriaBolsa categoriaBolsa) {
        this.categoriaBolsa = categoriaBolsa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.categoriaBolsa);
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
        final Bolsa other = (Bolsa) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.categoriaBolsa, other.categoriaBolsa)) {
            return false;
        }
        if (!Objects.equals(this.quantidade, other.quantidade)) {
            return false;
        }
        return true;
    }  
    
    public Bolsa buscarPeloId(Long id){
        return new BolsaDAO().findById(id);
    }
    
    public List<Bolsa> buscarTodos() {
        return new BolsaDAO().findAll();
    }
    
    public boolean salvar(){
        return new BolsaDAO().save(this);
    }
   
    public boolean remover(Long id) {
        return new BolsaDAO().remove(id);
    }
}
