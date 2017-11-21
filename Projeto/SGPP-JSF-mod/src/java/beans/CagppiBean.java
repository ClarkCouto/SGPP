/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Cagppi;
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
public class CagppiBean implements Serializable {    
    private Cagppi cagppi = new Cagppi(); 
    
    // Getters e Setters
    public Cagppi getCagppi() {
        return cagppi;
    }

    public void setCagppi(Cagppi cagppi) {
        this.cagppi = cagppi;
    }
    
    // Ações
    public String cadastrar() {
        try{
            cagppi.setAtivo(Boolean.TRUE);
            cagppi.setDataNascimento(new Date());
            cagppi.setSenha("1234");
            cagppi.setUltimoAcesso(new Date());
            cagppi.salvar();
            return "login";
        }
        catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar CAGPPI!",
                                   "Erro ao cadastrar CAGPPI!"));
            return "signup";
        }
    }
    
    public void limpar() {
        setCagppi(new Cagppi());
    }
}
