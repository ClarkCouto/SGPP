/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpSession;

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
    private Date dataUltimaAlteracao;
    
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

    public Date getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public String getAlteradoPor() {
        return alteradoPor;
    }

    public void setAlteradoPor(String alteradoPor) {
        this.alteradoPor = alteradoPor;
    }

    @PrePersist
    public void setCreationDate() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        String nome = usuario != null ? usuario.getNome() : "";
        this.setAlteradoPor(nome);
        this.setAtivo(Boolean.TRUE);
        this.setCriadoPor(nome);
        this.setDataCriacao(new Date());
        this.setDataUltimaAlteracao(new Date());
    }
    
    @PreUpdate
    public void setChangeDate() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        this.setAlteradoPor(usuario.getNome());
        this.setDataUltimaAlteracao(new Date());
    }  
               
}
