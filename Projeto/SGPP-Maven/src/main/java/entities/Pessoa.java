/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CristianoSilva
 */
@MappedSuperclass
public abstract class Pessoa extends BaseEntityAudit {
    private static final Long serialVersionUID = 5953225846505938118L;
    
    public static enum Sexo {
        Masculino, Feminino
    }
    
    @Column(nullable=false, columnDefinition = "VARCHAR(14)")
    private String cpf;   
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;
            
    @Column(nullable=false, columnDefinition = "VARCHAR(50)")
    private String email;
    
    @ManyToOne(cascade={CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Instituicao instituicao;
    
    @Column(nullable=false, columnDefinition = "VARCHAR(100)")
    private String nome;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Sexo sexo;
    
    @Column(nullable=true, columnDefinition = "VARCHAR(15)")
    private String telefoneCelular;
    
    @Column(nullable=true, columnDefinition = "VARCHAR(14)")
    private String telefoneFixo;
    
    public Pessoa() {
    }            

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }    

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.cpf);
        hash = 31 * hash + Objects.hashCode(this.dataNascimento);
        hash = 31 * hash + Objects.hashCode(this.email);
        hash = 31 * hash + Objects.hashCode(this.instituicao);
        hash = 31 * hash + Objects.hashCode(this.nome);
        hash = 31 * hash + Objects.hashCode(this.sexo);
        hash = 31 * hash + Objects.hashCode(this.telefoneCelular);
        hash = 31 * hash + Objects.hashCode(this.telefoneFixo);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefoneCelular, other.telefoneCelular)) {
            return false;
        }
        if (!Objects.equals(this.telefoneFixo, other.telefoneFixo)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimento, other.dataNascimento)) {
            return false;
        }
        if (!Objects.equals(this.instituicao, other.instituicao)) {
            return false;
        }
        if (this.sexo != other.sexo) {
            return false;
        }
        return true;
    } 
}
