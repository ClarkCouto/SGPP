/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.DocumentoDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CristianoSilva
 */
@Entity
public class Documento extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
   
    @Column(nullable=true)
    private String arquivoAnexo;
    
    @Column(nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntregue;
    
    @Column(nullable=false)
    private Boolean entregue;
    
    @ManyToOne(cascade={CascadeType.MERGE}, fetch = FetchType.LAZY)
    private TipoDocumento tipoDocumento;

    public Documento() {        
    }

    public String getArquivoAnexo() {
        return arquivoAnexo;
    }

    public void setArquivoAnexo(String arquivoAnexo) {
        this.arquivoAnexo = arquivoAnexo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataEntregue() {
        return dataEntregue;
    }

    public void setDataEntregue(Date dataEntregue) {
        this.dataEntregue = dataEntregue;
    }

    public Boolean getEntregue() {
        return entregue;
    }

    public void setEntregue(Boolean entregue) {
        this.entregue = entregue;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.arquivoAnexo);
        hash = 89 * hash + Objects.hashCode(this.ativo);
        hash = 89 * hash + Objects.hashCode(this.dataEntregue);
        hash = 89 * hash + Objects.hashCode(this.entregue);
        hash = 89 * hash + Objects.hashCode(this.tipoDocumento);
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
        final Documento other = (Documento) obj;
        if (!Objects.equals(this.arquivoAnexo, other.arquivoAnexo)) {
            return false;
        }
        if (!Objects.equals(this.ativo, other.ativo)) {
            return false;
        }
        if (!Objects.equals(this.dataEntregue, other.dataEntregue)) {
            return false;
        }
        if (!Objects.equals(this.entregue, other.entregue)) {
            return false;
        }
        if (!Objects.equals(this.tipoDocumento, other.tipoDocumento)) {
            return false;
        }
        return true;
    }  
    
    public Documento buscarPeloId(Long id){
        return new DocumentoDAO().findById(id);
    }
    
    public List<Documento> buscarTodos() {
        return new DocumentoDAO().findAll();
    }
   
    public boolean remover(Long id) {
        return new DocumentoDAO().remove(id);
    }
    
    public boolean salvar(){
        return new DocumentoDAO().save(this);
    }
}