/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dao.SetorDePesquisaDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author CristianoSilva
 */
@Entity
@DiscriminatorValue(value="SetorDePesquisa")
public class SetorDePesquisa extends Usuario implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
    
    public SetorDePesquisa() {
    }
    
    @Override
    public SetorDePesquisa buscarPeloId(Long id){
        return new SetorDePesquisaDAO().findById(id);
    }
    
    public List<SetorDePesquisa> buscarTodosSetores() {
        return new SetorDePesquisaDAO().findAll();
    }
}
