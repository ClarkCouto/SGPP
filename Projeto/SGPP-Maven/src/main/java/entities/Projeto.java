/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.ProjetoDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CristianoSilva
 */
@Entity
public class Projeto extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
    
    @Column(nullable=false)
    private boolean aipct;
    
    @Column(nullable=false)
    private int ano;
    
    @ManyToOne(cascade={CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Coordenador coordenador;
    
    @Column(nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;
    
    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
    
    @ManyToOne(cascade={CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Edital edital;
    
    @OneToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Lembrete> lembretes;
    
    @ManyToMany(cascade={CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable( name="PROJETO_ALUNOS", 
        joinColumns={@JoinColumn(name="IDPROJETO")}, 
        inverseJoinColumns={@JoinColumn(name="IDALUNO")})
    private List<Aluno> listaAlunos;
    
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable( name="PROJETO_COLABORADORES", 
        joinColumns={@JoinColumn(name="IDPROJETO")}, 
        inverseJoinColumns={@JoinColumn(name="IDCOLABORADORES")})
    private List<Colaborador> listaColaboradores;
    
    @Column(nullable=true, columnDefinition = "VARCHAR(100)")
    private String titulo;

    public Projeto() {
    }

    public boolean isAipct() {
        return aipct;
    }

    public void setAipct(boolean aipct) {
        this.aipct = aipct;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public List<Lembrete> getLembretes() {
        return lembretes;
    }

    public void setLembretes(List<Lembrete> lembretes) {
        this.lembretes = lembretes;
    }

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(List<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    public List<Colaborador> getListaColaboradores() {
        return listaColaboradores;
    }

    public void setListaColaboradores(List<Colaborador> listaColaboradores) {
        this.listaColaboradores = listaColaboradores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.aipct ? 1 : 0);
        hash = 53 * hash + this.ano;
        hash = 53 * hash + Objects.hashCode(this.coordenador);
        hash = 53 * hash + Objects.hashCode(this.dataFim);
        hash = 53 * hash + Objects.hashCode(this.dataInicio);
        hash = 53 * hash + Objects.hashCode(this.edital);
        hash = 53 * hash + Objects.hashCode(this.listaAlunos);
        hash = 53 * hash + Objects.hashCode(this.listaColaboradores);
        hash = 53 * hash + Objects.hashCode(this.titulo);
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
        final Projeto other = (Projeto) obj;
        if (this.aipct != other.aipct) {
            return false;
        }
        if (this.ano != other.ano) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.coordenador, other.coordenador)) {
            return false;
        }
        if (!Objects.equals(this.dataFim, other.dataFim)) {
            return false;
        }
        if (!Objects.equals(this.dataInicio, other.dataInicio)) {
            return false;
        }
        if (!Objects.equals(this.edital, other.edital)) {
            return false;
        }
        if (!Objects.equals(this.listaAlunos, other.listaAlunos)) {
            return false;
        }
        if (!Objects.equals(this.listaColaboradores, other.listaColaboradores)) {
            return false;
        }
        return true;
    }
    
    public Projeto buscarPeloId(Long id){
        return new ProjetoDAO().findById(id);
    }
    
    public List<Projeto> buscarTodos() {
        return new ProjetoDAO().findAll();
    }
   
    public boolean remover(Long id) {
        return new ProjetoDAO().remove(id);
    }
    
    public boolean salvar(){
        return new ProjetoDAO().save(this);
    }
}