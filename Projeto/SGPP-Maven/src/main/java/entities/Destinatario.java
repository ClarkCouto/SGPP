/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.DestinatarioDAO;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author CristianoSilva
 */
@Entity
@DiscriminatorValue(value="Destinatario")
public class Destinatario extends Pessoa implements Serializable {
    private static final long serialVersionUID = 5953225846505938118L;
        
    public static enum TipoDestinatario {
        Aluno, Colaborador
    }
          
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TipoDestinatario tipoDestinatario;

    public Destinatario() {
    }  

    public TipoDestinatario getTipoDestinatario() {
        return tipoDestinatario;
    }

    public void setTipoDestinatario(TipoDestinatario tipo) {
        this.tipoDestinatario = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.tipoDestinatario);
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
        final Destinatario other = (Destinatario) obj;
        if (this.tipoDestinatario != other.tipoDestinatario) {
            return false;
        }
        return true;
    }

//    public Destinatario buscarPeloId(Long id){
//        return new DestinatarioDAO().findById(id);
//    }
//    
//    public List<Destinatario> buscarTodos() {
//        return new DestinatarioDAO().findAll();
//    }
//   
//    public boolean remover(Long id) {
//        return new DestinatarioDAO().remove(id);
//    }  
//    
//    public boolean salvar(){
//        return new DestinatarioDAO().save(this);
//    }
    
// Outros métodos
    public Destinatario buscarPeloCpf(String cpf){
        return new DestinatarioDAO().findByCpf(cpf);
    }
}