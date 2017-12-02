/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.LembreteDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CristianoSilva
 */
@Entity
public class Lembrete extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;   
    
    @Column(nullable=false, columnDefinition = "VARCHAR(200)")
    private String descricao;
    
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Documento documento;
    
    @XmlTransient 
    public TipoDocumento tipoDocumento;
    
    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLimite;
    
    @Column(nullable=false)
    private boolean obrigatorio;

    public Lembrete() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.descricao);
        hash = 31 * hash + Objects.hashCode(this.documento);
        hash = 31 * hash + Objects.hashCode(this.dataLimite);
        hash = 31 * hash + (this.obrigatorio ? 1 : 0);
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
        final Lembrete other = (Lembrete) obj;
        if (this.obrigatorio != other.obrigatorio) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        if (!Objects.equals(this.dataLimite, other.dataLimite)) {
            return false;
        }
        return true;
    }
    
    public Lembrete buscarPeloId(Long id){
        return new LembreteDAO().findById(id);
    }
    
    public List<Lembrete> buscarTodos() {
        return new LembreteDAO().findAll();
    }
   
    public boolean remover(Long id) {
        return new LembreteDAO().remove(id);
    }
    
    public boolean salvar(){
        return new LembreteDAO().save(this);
    }
}