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
import entities.Usuario.TipoUsuario;
import java.io.Serializable;
import java.util.ArrayList;
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
        return this.listaFiltrada;
    }
 
    public void setListaFiltrada(List<Coordenador> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public List<SelectItem> getAreasOptions(){
        List<SelectItem> items = new ArrayList<>();  
        new Area().buscarTodos().forEach((c) -> {
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
        return this.grupoDePesquisaSelecionado;
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
        return this.instituicoes;
    }
    
    public Coordenador getCoordenador() {
        return this.coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public void setCoordenadorSelecionado(Coordenador coordenador) {
        this.coordenadorSelecionado = coordenador;
    }
    
    public List<Coordenador> getCoordenadores(){
        this.coordenadores = new Coordenador().buscarTodosCoordenadores();
        return this.coordenadores;
    }
    
    public void setCoordenadores(List<Coordenador> coordenadores){
        this.coordenadores = coordenadores;
    }
    
    // Ações    
    public String detalhar(Long id){
        if(id != null)
            this.coordenadorSelecionado = this.coordenador.buscarPeloId(id);

        if (this.coordenadorSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Coordenador!",
                                   "Erro ao localizar Coordenador!"));
            return "/pages/listar/listarCoordenadores";
        }
        else {
            this.coordenador = this.coordenadorSelecionado;
            return "/pages/detalhes/detalhesCoordenador?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            this.coordenadorSelecionado = this.coordenador.buscarPeloId(id);

        if (this.coordenadorSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Coordenador!",
                                   "Erro ao localizar Coordenador!"));
            return "/pages/listar/listarCoordenadores";
        }
        else {
            this.editando = true;
            this.coordenador = this.coordenadorSelecionado;
            return "/pages/editar/editarCoordenador?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.coordenador = new Coordenador();
        this.coordenadorSelecionado = new Coordenador();
    }
    
    public String remover(Long id) {
        if(this.coordenador.remover(id))
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
        this.coordenador.setGruposDePesquisa(gruposDePesquisa);
        if(this.editando){
            if(this.coordenador.salvar())
                return "/pages/listar/listarCoordenadores?faces-redirect=true";
            else {
                FacesContext.getCurrentInstance().addMessage(null,
                           new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao editar Coordenador!",
                                       "Erro ao editar Coordenador!"));
                return "/pages/editar/editarCoordenador";
            }
        }
        else if(validarCpfUnico(coordenador.getCpf())){
            this.coordenador.setSenha("1234");
            this.coordenador.setTipo(TipoUsuario.Coordenador);
            if(this.coordenador.salvar())
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
