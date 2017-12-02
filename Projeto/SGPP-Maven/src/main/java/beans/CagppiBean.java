/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Cagppi;
import entities.Usuario;
import entities.Usuario.TipoUsuario;
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
public class CagppiBean implements Serializable {    
    private Cagppi cagppi = new Cagppi(); 
    private Cagppi cagppiSelecionado;
    private List<Cagppi> cagppis;
    private List<Cagppi> listaFiltrada;
    private Boolean editando;
    
    // Getters e Setters
    public List<Cagppi> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Cagppi> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public Cagppi getCagppi() {
        return cagppi;
    }

    public void setCagppi(Cagppi cagppi) {
        this.cagppi = cagppi;
    }

    public void setCagppiSelecionado(Cagppi cagppi) {
        this.cagppiSelecionado = cagppi;
    }
    
    public List<Cagppi> getCagppis(){
        this.cagppis = new Cagppi().buscarTodosCagppis();
        return cagppis;
    }
    
    // Ações
    public String detalhar(Long id){
        if(id != null)
            cagppiSelecionado = this.cagppi.buscarPeloId(id);

        if (cagppiSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar CAGPPI!",
                                   "Erro ao localizar CAGPPI!"));
            return "/pages/listar/listarCagppi";
        }
        else {
            this.cagppi = cagppiSelecionado;
            return "/pages/detalhes/detalhesCagppi?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            cagppiSelecionado = this.cagppi.buscarPeloId(id);

        if (cagppiSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar CAGPPI!",
                                   "Erro ao localizar CAGPPI!"));
            return "/pages/listar/listarCagppi";
        }
        else {
            this.cagppi = cagppiSelecionado;
            return "/pages/editar/editarCagppi?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.editando = false;
        this.cagppi = new Cagppi();
        this.cagppiSelecionado = new Cagppi();
    }
    
    public String remover(Long id) {
        if(cagppi.remover(id))
            return "/pages/listar/listarCagppi?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir CAGPPI!",
                                   "Erro ao excluir CAGPPI!"));
            return "/pages/listar/listarCagppi";
        }
    }
    
    public String salvar() {
        if(validarCpfUnico(cagppi.getCpf())){
            cagppi.setAtivo(Boolean.TRUE);
            cagppi.setDataNascimento(new Date());
            cagppi.setSenha("1234");
            cagppi.setTipo(TipoUsuario.CAGPPI);
            cagppi.setUltimoAcesso(new Date());
            if(cagppi.salvar())
                return "/pages/listar/listarCagppi?faces-redirect=true";
            else {
                FacesContext.getCurrentInstance().addMessage(null,
                           new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar CAGPPi!",
                                       "Erro ao salvar CAGPPI!"));
                return "/pages/cadastrar/cadastrarCagppi";
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Coordenador!",
                                   "Já existe um Usuário cadastrado com este CPF!"));
            return "/pages/cadastrar/cadastrarCoordenador";
        }
    }
    
    public boolean validarCpfUnico(String cpf){
        Usuario user = new Usuario().buscarPeloCpf(cpf);
        if(user == null)
            return true;
        return false;
    }
}
