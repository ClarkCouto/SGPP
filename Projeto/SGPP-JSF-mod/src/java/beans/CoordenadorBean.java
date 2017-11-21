/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Coordenador;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author CristianoSilva
 */
@ManagedBean
@RequestScoped
public class CoordenadorBean implements Serializable {    
    private Coordenador coordenador = new Coordenador(); 
    
    // Getters e Setters
    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }
    
    // Ações
    public String cadastrar() {
        try{
            coordenador.setAtivo(Boolean.TRUE);
            coordenador.setDataNascimento(new Date());
            coordenador.setSenha("1234");
            coordenador.setUltimoAcesso(new Date());
            coordenador.salvar();
            return "login";
        }
        catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar Coordenador!",
                                   "Erro ao cadastrar Coordenador!"));
            return "signup";
        }
    }
    
    public void limpar() {
        setCoordenador(new Coordenador());
    }
}
