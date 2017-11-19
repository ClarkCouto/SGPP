/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import utils.PaginasEnum;

/**
 *
 * @author matheus
 */
@ManagedBean
@ApplicationScoped
public class NavegacaoBean {
    
    private PaginasEnum paginaAtual;
    private final PaginasEnum[] paginas = PaginasEnum.values();

    public PaginasEnum getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(PaginasEnum pagina) {
        this.paginaAtual = pagina;
    }
    
    public PaginasEnum[] getPaginas() {
        return paginas;
    }
    
    public void atualizaPagina(String parametroPagina) {        

        if (parametroPagina != null && !parametroPagina.isEmpty())
            this.setPaginaAtual(PaginasEnum.valueOf(parametroPagina));
        
    }
    
}