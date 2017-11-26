/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Area;
import entities.Coordenador;
import entities.GrupoDePesquisa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
    private List<Area> areas; 
    private List<Coordenador> coordenadores; 
    private List<Coordenador> listaFiltrada;
    private List<GrupoDePesquisa> gruposDePesquisa;
    private Boolean editando;
    
// Construtor
    public CoordenadorBean() {
        this.gruposDePesquisa = this.coordenador.getGruposDePesquisa();
        if(this.gruposDePesquisa == null)
            this.gruposDePesquisa = new ArrayList<>();
    }
    
    // Getters e Setters
    public List<Coordenador> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<Coordenador> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public List<SelectItem> getAreas(){
        this.areas = new Area().buscarTodos();
        List<SelectItem> items = new ArrayList<>();  
        this.areas.forEach((c) -> {
            items.add(new SelectItem(c, c.getNome()));
        }); 
        return items;
    }
    
    public List<SelectItem> getGruposDePesquisa(){
        this.gruposDePesquisa = new GrupoDePesquisa().buscarTodos();
        List<SelectItem> items = new ArrayList<>();  
        this.gruposDePesquisa.forEach((c) -> {
            items.add(new SelectItem(c, c.getNome()));
        }); 
        return items;
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

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
    // Ações
    public void adicionarGrupoDePesquisa() {
        this.gruposDePesquisa.add(new GrupoDePesquisa());
        this.coordenador.setGruposDePesquisa(gruposDePesquisa);
    }
    
    public void removerGrupoDePesquisa(GrupoDePesquisa grupo) {
        this.gruposDePesquisa.remove(grupo);
        this.coordenador.setGruposDePesquisa(this.gruposDePesquisa);
    }   
    
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

        if (coordenadorSelecionado != null) {
            this.coordenador = coordenadorSelecionado;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "/pages/editar/editarCoordenador?faces-redirect=true";
    }  
    
    public void limpar(){
        this.editando = false;
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
        coordenador.setAtivo(Boolean.TRUE);
        coordenador.setDataNascimento(new Date());
        coordenador.setGruposDePesquisa(gruposDePesquisa);
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
}
