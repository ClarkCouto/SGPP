/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Area;
import entities.Coordenador;
import entities.GrupoDePesquisa;
import entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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
    private List<Coordenador> coordenadores; 
    private List<Coordenador> listaFiltrada;
    private List<GrupoDePesquisa> gruposDePesquisa;
    private List<GrupoDePesquisa> gruposDePesquisaOptions;
    private List<GrupoDePesquisa> gruposDePesquisaSelecionados;
    
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
            this.gruposDePesquisaSelecionados = new ArrayList<>();  
        }
        return gruposDePesquisa;
    }

    public List<GrupoDePesquisa> getGruposDePesquisaSelecionados() {
        return gruposDePesquisaSelecionados;
    }

    public void setGruposDePesquisa(List<GrupoDePesquisa> gruposDePesquisa) {
        this.gruposDePesquisa = gruposDePesquisa;
    }
    
    public List<SelectItem> getGruposDePesquisaOptions(){        
        this.gruposDePesquisaOptions = new GrupoDePesquisa().buscarTodos();
        List<SelectItem> items = new ArrayList<>();
        this.gruposDePesquisaOptions.forEach((c) -> {
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
    
    // Ações
    public void adicionarGrupoDePesquisa() {
        this.gruposDePesquisa.add(new GrupoDePesquisa());
        this.coordenador.setGruposDePesquisa(gruposDePesquisa);
    }
    
    public void removerGrupoDePesquisa(GrupoDePesquisa grupo) {
        this.gruposDePesquisa.remove(grupo);
        this.coordenador.setGruposDePesquisa(this.gruposDePesquisa);
    }   
    
//    public String atualizarGrupoSelecionado(ValueChangeEvent event) {
//        GrupoDePesquisa grupo = this.gruposDePesquisaOptions.get(Integer.parseInt(event.getNewValue().toString().replaceAll("\\D+",""))-1);
//        Integer num = Integer.parseInt(event.getOldValue().toString().replaceAll("\\D+",""))-1;
//        if(num != -1){
//            if(!this.gruposDePesquisaSelecionados.contains(grupo)){
//                this.gruposDePesquisaSelecionados.add(grupo);
//                this.coordenador.setGruposDePesquisa(this.gruposDePesquisaSelecionados);
//            }
//            else{
//                FacesContext.getCurrentInstance().addMessage(null,
//                           new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este Grupo de Pesquisa já foi adicionado anteriormente",
//                                       "Este Grupo de Pesquisa já foi adicionado anteriormente!"));
//            }
//        }
//        else{
//            this.gruposDePesquisaSelecionados.add(grupo);
//            this.coordenador.setGruposDePesquisa(this.gruposDePesquisaSelecionados);
//        }
//        return "/pages/cadastrar/cadastrarCoordenador";
//    } 
    
    public String atualizarGrupoSelecionado(ValueChangeEvent event) {
        GrupoDePesquisa grupo = null;
        Long novo = Long.parseLong(event.getNewValue().toString().replaceAll("\\D+",""));
        for(GrupoDePesquisa g : gruposDePesquisaOptions){
            if(g.getId() == novo)
                grupo = g;
        }
        Long id;
        if(event.getOldValue().toString().replaceAll("\\D+","").equals(""))
            id = null;
        else
            id = Long.parseLong(event.getOldValue().toString().replaceAll("\\D+",""));
        
        if(id != null){
            if(!this.gruposDePesquisaSelecionados.contains(grupo)){
                for(int i = 0; i < gruposDePesquisaSelecionados.size(); i++){
                    if(gruposDePesquisaSelecionados.get(i).getId() == id)
                        gruposDePesquisaSelecionados.set(i, grupo);
                }
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null,
                           new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este Grupo de Pesquisa já foi adicionado anteriormente",
                                       "Este Grupo de Pesquisa já foi adicionado anteriormente!"));
            }
        }
        else{
            this.gruposDePesquisaSelecionados.add(grupo);
        }
        this.gruposDePesquisa = this.gruposDePesquisaSelecionados;
        this.coordenador.setGruposDePesquisa(this.gruposDePesquisaSelecionados);
        return "/pages/cadastrar/cadastrarCoordenador";
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

        if (coordenadorSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Coordenador!",
                                   "Erro ao localizar Coordenador!"));
            return "/pages/listar/listarCoordenadores";
        }
        else {
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
        if(validarCpfUnico(coordenador.getCpf())){
            coordenador.setAtivo(Boolean.TRUE);
            coordenador.setDataNascimento(new Date());
            coordenador.setGruposDePesquisa(gruposDePesquisaSelecionados);
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
