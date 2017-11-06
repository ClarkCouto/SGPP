/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author CristianoSilva
 */
@Entity
public class Edital extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
    
    @OneToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Collection<Lembrete> lembretes;
    
    @OneToMany(cascade={CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Collection<Bolsa> bolsas;

    @Column(nullable=false, columnDefinition = "VARCHAR(50)")
    private String numero;
    
    @Column(nullable=false, columnDefinition = "VARCHAR(50)")
    private String origem;
    
    @Column(nullable=true)
    private String pdf;
    
    @Column(nullable=false, columnDefinition = "VARCHAR(100)")
    private String titulo;

    public Edital() {    
    }
    
    public Collection<Lembrete> getLembretes() {
        return lembretes;
    }

    public void setLembretes(Collection<Lembrete> lembretes) {
        this.lembretes = lembretes;
    }

    public Collection<Bolsa> getBolsas() {
        return bolsas;
    }

    public void setBolsas(Collection<Bolsa> bolsas) {
        this.bolsas = bolsas;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.lembretes);
        hash = 29 * hash + Objects.hashCode(this.bolsas);
        hash = 29 * hash + Objects.hashCode(this.numero);
        hash = 29 * hash + Objects.hashCode(this.origem);
        hash = 29 * hash + Objects.hashCode(this.pdf);
        hash = 29 * hash + Objects.hashCode(this.titulo);
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
        final Edital other = (Edital) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.origem, other.origem)) {
            return false;
        }
        if (!Objects.equals(this.pdf, other.pdf)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.lembretes, other.lembretes)) {
            return false;
        }
        if (!Objects.equals(this.bolsas, other.bolsas)) {
            return false;
        }
        return true;
    }
        
}
