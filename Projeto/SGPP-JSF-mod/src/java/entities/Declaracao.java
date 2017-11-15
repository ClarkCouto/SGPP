/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.DeclaracaoDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CristianoSilva
 */
@Entity
public class Declaracao extends BaseEntityAudit implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
    
    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;
    
    @ManyToOne
    private Usuario destinatario;
    
    @ManyToOne
    private Projeto projeto;
    
    @ManyToOne
    private Coordenador responsavel;
    
    @ManyToOne
    private TextoBaseDeclaracao textoBaseDeclaracao;

    public Declaracao() {
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Pessoa getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Coordenador getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Coordenador responsavel) {
        this.responsavel = responsavel;
    }

    public TextoBaseDeclaracao getTextoBaseDeclaracao() {
        return textoBaseDeclaracao;
    }

    public void setTextoBaseDeclaracao(TextoBaseDeclaracao textoBaseDeclaracao) {
        this.textoBaseDeclaracao = textoBaseDeclaracao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.dataEmissao);
        hash = 19 * hash + Objects.hashCode(this.destinatario);
        hash = 19 * hash + Objects.hashCode(this.projeto);
        hash = 19 * hash + Objects.hashCode(this.responsavel);
        hash = 19 * hash + Objects.hashCode(this.textoBaseDeclaracao);
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
        final Declaracao other = (Declaracao) obj;
        if (!Objects.equals(this.dataEmissao, other.dataEmissao)) {
            return false;
        }
        if (!Objects.equals(this.destinatario, other.destinatario)) {
            return false;
        }
        if (!Objects.equals(this.projeto, other.projeto)) {
            return false;
        }
        if (!Objects.equals(this.responsavel, other.responsavel)) {
            return false;
        }
        if (!Objects.equals(this.textoBaseDeclaracao, other.textoBaseDeclaracao)) {
            return false;
        }
        return true;
    }
    
    public Declaracao buscarPeloId(Long id){
        return new DeclaracaoDAO().findById(id);
    }
    
    public List<Declaracao> buscarTodos() {
        return new DeclaracaoDAO().findAll();
    }
   
    public boolean remover(Long id) {
        return new DeclaracaoDAO().remove(id);
    }
    
    public boolean salvar(){
        return new DeclaracaoDAO().save(this);
    }
}