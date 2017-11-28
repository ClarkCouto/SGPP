/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Area;
import entities.Coordenador;
import entities.GrupoDePesquisa;
import entities.Instituicao;
import entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author CristianoSilva
 */
@ManagedBean
@ApplicationScoped
public class CoordenadorBean implements Serializable {    
    private Coordenador coordenador = new Coordenador();
    private Coordenador coordenadorSelecionado;
    private GrupoDePesquisa grupoDePesquisaSelecionado;
    private List<Coordenador> coordenadores; 
    private List<Coordenador> listaFiltrada;
    private List<GrupoDePesquisa> gruposDePesquisa;
    private List<GrupoDePesquisa> gruposDePesquisaOptions;
    private List<Instituicao> instituicoes;
    private boolean editando;
    
    // Getters e Setters
    public List<Coordenador> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Coordenador> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public List<SelectItem> getAreasOptions(){
        List<Area> areas = new Area().buscarTodos();
        List<SelectItem> items = new ArrayList<>();  
        areas.forEach((c) -> {
            items.add(new SelectItem(c, c.getNome()));
        }); 
        return items;
    }    

    public List<GrupoDePesquisa> getGruposDePesquisa() {
        this.gruposDePesquisa = this.coordenador.getGruposDePesquisa();
        if(this.gruposDePesquisa == null){
            this.gruposDePesquisa = new ArrayList<>();  
        }
        return gruposDePesquisa;
    }

    public void setGruposDePesquisa() {
        getGruposDePesquisa();
        if(!this.gruposDePesquisa.contains(this.grupoDePesquisaSelecionado))
            this.gruposDePesquisa.add(this.grupoDePesquisaSelecionado);
    }

    public GrupoDePesquisa getGrupoDePesquisaSelecionado() {
        return grupoDePesquisaSelecionado;
    }

    public void setGrupoDePesquisaSelecionado(GrupoDePesquisa grupoDePesquisaSelecionado) {
        this.grupoDePesquisaSelecionado = grupoDePesquisaSelecionado;
    }
    
    public List<SelectItem> getGruposDePesquisaOptions(){   
        this.gruposDePesquisaOptions = new GrupoDePesquisa().buscarTodos();
        List<SelectItem> items = new ArrayList<>();
        this.gruposDePesquisaOptions.forEach((c) -> {
            items.add(new SelectItem(c, c.getNome()));
        });
        return items;
    } 

    public List<Instituicao> getInstituicoes() {
        this.instituicoes = new Instituicao().buscarTodos();
        if(this.instituicoes == null){
            this.instituicoes = new ArrayList<>();  
        }
        return instituicoes;
    }
    
    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public void setCoordenadorSelecionado(Coordenador coordenador) {
        this.coordenadorSelecionado = coordenador;
    }
    
    public List<Coordenador> getCoordenadores(){
        this.coordenadores = new Coordenador().buscarTodosCoordenadores();
        return coordenadores;
    }
    
    public void setCoordenadores(List<Coordenador> coordenadores){
        this.coordenadores = coordenadores;
    }
    
    // Ações    
    public String detalhar(Long id){
        if(id != null)
            coordenadorSelecionado = this.coordenador.buscarPeloId(id);

        if (coordenadorSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Coordenador!",
                                   "Erro ao localizar Coordenador!"));
            return "/pages/listar/listarCoordenadores";
        }
        else {
            this.coordenador = coordenadorSelecionado;
            return "/pages/detalhes/detalhesCoordenador?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            coordenadorSelecionado = this.coordenador.buscarPeloId(id);

        if (coordenadorSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Coordenador!",
                                   "Erro ao localizar Coordenador!"));
            return "/pages/listar/listarCoordenadores";
        }
        else {
            this.editando = true;
            this.coordenador = coordenadorSelecionado;
            return "/pages/editar/editarCoordenador?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.coordenador = new Coordenador();
        this.coordenadorSelecionado = new Coordenador();
    }
    
    public String remover(Long id) {
        if(coordenador.remover(id))
            return "/pages/listar/listarCoordenadores?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Coordenador!",
                                   "Erro ao excluir Coordenador!"));
            return "/pages/listar/listarCoordenadores";
        }
    }
    
    public String salvar() {
        setGruposDePesquisa();
        coordenador.setGruposDePesquisa(gruposDePesquisa);
        if(this.editando){
            if(coordenador.salvar())
                return "/pages/listar/listarCoordenadores?faces-redirect=true";
            else {
                FacesContext.getCurrentInstance().addMessage(null,
                           new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao editar Coordenador!",
                                       "Erro ao editar Coordenador!"));
                return "/pages/editar/editarCoordenador";
            }
        }
        else if(validarCpfUnico(coordenador.getCpf())){
            coordenador.setAtivo(Boolean.TRUE);
            coordenador.setSenha("1234");
            coordenador.setTipo("Coordenador");
            coordenador.setUltimoAcesso(new Date());
            if(coordenador.salvar())
                return "/pages/listar/listarCoordenadores?faces-redirect=true";
            else {
                FacesContext.getCurrentInstance().addMessage(null,
                           new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Coordenador!",
                                       "Erro ao salvar Coordenador!"));
                return "/pages/cadastrar/cadastrarCoordenador";
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Já existe um Usuário cadastrado com este CPF!",
                                   "Teste"));
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
