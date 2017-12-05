/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.ColaboradorDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author CristianoSilva
 */
@Entity
@DiscriminatorValue(value="Colaborador")
public class Colaborador extends Destinatario implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
    
    public Colaborador buscarPeloId(Long id){
        return new ColaboradorDAO().findById(id);
    }
    
    public List<Colaborador> buscarTodos() {
        return new ColaboradorDAO().findAll();
    }
   
    public boolean remover(Long id) {
        return new ColaboradorDAO().remove(id);
    }  
    
    public boolean salvar(){
        this.setTipoDestinatario(Destinatario.TipoDestinatario.Colaborador);
        return new ColaboradorDAO().save(this);
    }
}
