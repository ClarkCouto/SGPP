/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.GrupoDePesquisaDAO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author CristianoSilva
 */
@Entity
public class GrupoDePesquisa extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
    
//    @ManyToOne
//    private Coordenador coordenador;
    
    @Column(nullable=false, columnDefinition = "VARCHAR(50)")
    private String nome;

    public GrupoDePesquisa() {
    }

//    public Coordenador getCoordenador() {
//        return coordenador;
//    }
//
//    public void setCoordenador(Coordenador coordenador) {
//        this.coordenador = coordenador;
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
//        hash = 23 * hash + Objects.hashCode(this.coordenador);
        hash = 23 * hash + Objects.hashCode(this.nome);
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
        final GrupoDePesquisa other = (GrupoDePesquisa) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
//        if (!Objects.equals(this.coordenador, other.coordenador)) {
//            return false;
//        }
        return true;
    }
    
    public GrupoDePesquisa buscarPeloId(Long id){
        return new GrupoDePesquisaDAO().findById(id);
    }
    
    public List<GrupoDePesquisa> buscarTodos() {
        return new GrupoDePesquisaDAO().findAll();
    }
   
    public boolean remover(Long id) {
        return new GrupoDePesquisaDAO().remove(id);
    }
    
    public boolean salvar(){
        return new GrupoDePesquisaDAO().save(this);
    }
}
