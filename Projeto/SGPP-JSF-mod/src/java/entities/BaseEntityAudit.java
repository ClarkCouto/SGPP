/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author matheus
 */
@MappedSuperclass
public class BaseEntityAudit extends BaseEntity {
    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    
    private String criadoPor;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    
    private String alteradoPor;
    
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getAlteradoPor() {
        return alteradoPor;
    }

    public void setAlteradoPor(String alteradoPor) {
        this.alteradoPor = alteradoPor;
    }

    @PrePersist
    public void setCreationDate() {
        this.setDataCriacao(new Date());
    }
    
    @PreUpdate
    public void setChangeDate() {
        this.setDataAlteracao(new Date());
    }  
               
}
