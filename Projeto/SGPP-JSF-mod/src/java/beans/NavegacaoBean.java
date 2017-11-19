/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import utils.Aba;
import utils.PaginasEnum;

/**
 *
 * @author matheus
 */
@ManagedBean
@RequestScoped
public class NavegacaoBean {
    
    private PaginasEnum paginaAtual;
    Collection<Aba> abas;

    public PaginasEnum getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(PaginasEnum pagina) {
        this.paginaAtual = pagina;
    }
    
    
    public Collection<Aba> getAbas () {                
        return this.abas;
    }
    
}