/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Calendar;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author mathe
 */
@Entity
public class Edital implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false)
    private String numero;
    
    @Column(nullable = false)
    private String origem;
    
    @Column
    private String pdf;
    
    @Column
    private Boolean ativo;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataCadastro;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataUltimaAlteracao;
    
    @OneToMany(mappedBy = "edital", cascade = CascadeType.ALL)
    private Collection<Lembrete> lembretes;
          
// Getters & Setters

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Calendar getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Calendar dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public Collection<Lembrete> getLembretes() {
        return lembretes;
    }

    public void setLembretes(Collection<Lembrete> lembretes) {
        this.lembretes = lembretes;
    }
        
    
// Outros métodos

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.titulo);
        hash = 61 * hash + Objects.hashCode(this.numero);
        hash = 61 * hash + Objects.hashCode(this.origem);
        hash = 61 * hash + Objects.hashCode(this.pdf);
        hash = 61 * hash + Objects.hashCode(this.ativo);
        hash = 61 * hash + Objects.hashCode(this.dataCadastro);
        hash = 61 * hash + Objects.hashCode(this.dataUltimaAlteracao);
        hash = 61 * hash + Objects.hashCode(this.lembretes);
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
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.origem, other.origem)) {
            return false;
        }
        if (!Objects.equals(this.pdf, other.pdf)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ativo, other.ativo)) {
            return false;
        }
        if (!Objects.equals(this.dataCadastro, other.dataCadastro)) {
            return false;
        }
        if (!Objects.equals(this.dataUltimaAlteracao, other.dataUltimaAlteracao)) {
            return false;
        }
        if (!Objects.equals(this.lembretes, other.lembretes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Edital{" + "id=" + id + ", titulo=" + titulo + ", numero=" + numero + ", origem=" + origem + ", pdf=" + pdf + ", ativo=" + ativo + ", dataCadastro=" + dataCadastro + ", dataUltimaAlteracao=" + dataUltimaAlteracao + ", lembretes=" + lembretes + '}';
    }

    
    
}
