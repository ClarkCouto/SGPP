/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.AlunoDAO;
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
public class Aluno extends Pessoa implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
   
    @Column(nullable=false)
    private Boolean bolsista;
    
    @ManyToOne(cascade={CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Bolsa bolsa;
    
    @ManyToOne(cascade={CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Curso curso;

    public Aluno() {
    }    

    public Boolean getBolsista() {
        return bolsista;
    }

    public void setBolsista(Boolean bolsista) {
        this.bolsista = bolsista;
    }

    public Bolsa getBolsa() {
        return bolsa;
    }

    public void setBolsa(Bolsa bolsa) {
        this.bolsa = bolsa;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.bolsa);
        hash = 73 * hash + Objects.hashCode(this.curso);
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
        final Aluno other = (Aluno) obj;
        if (!Objects.equals(this.bolsa, other.bolsa)) {
            return false;
        }
        if (!Objects.equals(this.curso, other.curso)) {
            return false;
        }
        return true;
    }
   
    public boolean remover(Long id) {
        return new AlunoDAO().remove(id);
    }  
    
    public boolean salvar(){
        return new AlunoDAO().save(this);
    }
}
