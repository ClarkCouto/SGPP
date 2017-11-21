/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.SetorDePesquisa;
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
public class SetorDePesquisaBean implements Serializable {    
    private SetorDePesquisa setorDePesquisa = new SetorDePesquisa(); 
    
    // Getters e Setters
    public SetorDePesquisa getSetorDePesquisa() {
        return setorDePesquisa;
    }

    public void setSetorDePesquisa(SetorDePesquisa setorDePesquisa) {
        this.setorDePesquisa = setorDePesquisa;
    }
    
    // Ações
    public String cadastrar() {
        try{
            setorDePesquisa.setAtivo(Boolean.TRUE);
            setorDePesquisa.setDataNascimento(new Date());
            setorDePesquisa.setSenha("1234");
            setorDePesquisa.setUltimoAcesso(new Date());
            setorDePesquisa.salvar();
            
//            SetorDePesquisa setor = new SetorDePesquisa();
//            setor.setAtivo(Boolean.TRUE);
//            setor.setCpf(setorDePesquisa.getCpf());
//            setor.setDataCriacao(new Date());
//            setor.setDataNascimento(new Date());
//            setor.setDataUltimaAlteracao(new Date());
//            setor.setEmail(setorDePesquisa.getEmail());
//            setor.setNome(setorDePesquisa.getNome());
//            setor.setSenha("1234");
//            setor.setSexo(setorDePesquisa.getSexo());
//            setor.setTelefoneCelular(setorDePesquisa.getTelefoneCelular());
//            setor.setTelefoneFixo(setorDePesquisa.getTelefoneFixo());
//            setor.setUltimoAcesso(new Date());
//            setor.salvar();
            
            return "login";
        }
        catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar Setor De Pesquisa!",
                                   "Erro ao cadastrar Setor De Pesquisa!"));
            return "signup";
        }
    }
    
    public void limpar() {
        setSetorDePesquisa(new SetorDePesquisa());
    }
}
