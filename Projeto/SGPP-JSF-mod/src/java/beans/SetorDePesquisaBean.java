/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.SetorDePesquisa;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author CristianoSilva
 */
@ManagedBean
@SessionScoped
public class SetorDePesquisaBean implements Serializable {    
    private SetorDePesquisa setorDePesquisa = new SetorDePesquisa(); 
    private SetorDePesquisa setorDePesquisaSelecionado;
    private List<SetorDePesquisa> setoresDePesquisa;
    private List<SetorDePesquisa> listaFiltrada;
    private Boolean editando;
    
    // Getters e Setters
    public List<SetorDePesquisa> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<SetorDePesquisa> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public SetorDePesquisa getSetorDePesquisa() {
        return setorDePesquisa;
    }

    public void setSetorDePesquisa(SetorDePesquisa setorDePesquisa) {
        this.setorDePesquisa = setorDePesquisa;
    }

    public void setCagppiSelecionado(SetorDePesquisa setorDePesquisa) {
        this.setorDePesquisaSelecionado = setorDePesquisa;
    }
    
    public List<SetorDePesquisa> getSetoresDePesquisa(){
        this.setoresDePesquisa = new SetorDePesquisa().buscarTodosSetores();
        return setoresDePesquisa;
    }
    
    // Ações
    public String detalhar(Long id){
        if(id != null)
            setorDePesquisaSelecionado = this.setorDePesquisa.buscarPeloId(id);

        if (setorDePesquisaSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Setor De Pesquisa!",
                                   "Erro ao localizar Setor De Pesquisa!"));
            return "/pages/listar/listarSetoresDePesquisa";
        }
        else {
            this.setorDePesquisa = setorDePesquisaSelecionado;
            return "/pages/detalhes/detalhesSetorDePesquisa?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            setorDePesquisaSelecionado = this.setorDePesquisa.buscarPeloId(id);

        if (setorDePesquisaSelecionado != null) {
            this.setorDePesquisa = setorDePesquisaSelecionado;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "/pages/editar/editarSetoresDePesquisa?faces-redirect=true";
    }  
    
    public void limpar(){
        this.editando = false;
        this.setorDePesquisa = new SetorDePesquisa();
        this.setorDePesquisaSelecionado = new SetorDePesquisa();
    }
    
    public String remover(Long id) {
        if(setorDePesquisa.remover(id))
            return "/pages/listar/listarSetoresDePesquisa?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Setor De Pesquisa!",
                                   "Erro ao excluir Setor De Pesquisa!"));
            return "/pages/listar/listarSetoresDePesquisa";
        }
    }
    
    public String salvar() {
        setorDePesquisa.setAtivo(Boolean.TRUE);
        setorDePesquisa.setDataNascimento(new Date());
        setorDePesquisa.setSenha("1234");
        setorDePesquisa.setTipo("SetorDePesquisa");
        setorDePesquisa.setUltimoAcesso(new Date());
        if(setorDePesquisa.salvar())
            return "/pages/listar/listarSetoresDePesquisa?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Setor De Pesquisa!",
                                   "Erro ao salvar Setor De Pesquisa!"));
            return "/pages/cadastrar/cadastrarSetorDePesquisa";
        }
    }
}
