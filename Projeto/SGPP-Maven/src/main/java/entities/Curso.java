/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.CursoDAO;
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
public class Curso extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L; 
    
    @Column(nullable=false, columnDefinition = "VARCHAR(50)")
    private String nome;
    
    @ManyToOne(cascade={CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Instituicao instituicao;

    public Curso() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + Objects.hashCode(this.instituicao);
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
        final Curso other = (Curso) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.instituicao, other.instituicao)) {
            return false;
        }
        return true;
    }
    
    public Curso buscarPeloId(Long id){
        return new CursoDAO().findById(id);
    }
    
    public List<Curso> buscarPelaInstituicao(Long id){
        return new CursoDAO().findByInstituicao(id);
    }
    
    public List<Curso> buscarTodos() {
        return new CursoDAO().findAll();
    }
   
    public boolean remover(Long id) {
        return new CursoDAO().remove(id);
    }
    
    public boolean salvar(){
        return new CursoDAO().save(this);
    }
}