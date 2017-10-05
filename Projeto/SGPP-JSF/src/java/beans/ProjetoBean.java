/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import models.Projeto;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author CristianoSilva
 */
@ManagedBean
@SessionScoped
public class ProjetoBean {
    private Projeto projeto = new Projeto();
    private Projeto projetoSelecionado = new Projeto();
    private LinkedList<Projeto> lista = new LinkedList<>();    
    
    private String edital;
    private Map<Long, String> editais = new HashMap<>();
    
    @PostConstruct
    public void init() {
        projetoSelecionado = projeto;
        lista.add(new Projeto("false", "2017", "true", "Início", "Fim" , "Edital 1", "Projeto 1"));
        lista.add(new Projeto("false", "2015", "false", "Início", "Fim", "Edital 1", "Projeto 2"));
        
        editais = new HashMap<>();
        editais.put(1L, "Edital 1");     
        editais.put(2L, "Edital 2");
        editais.put(3L, "Edital 3");  
    }

    public Projeto getProjeto() {        
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Projeto getProjetoSelecionado() {
        return projetoSelecionado;
    }

    public void setProjetoSelecionado(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
    }

    public String getEdital() {
        return edital;
    }

    public void setEdital(String edital) {
        this.edital = edital;
    }

    public Map<Long, String> getEditais() {
        return editais;
    }

    public void setEditais(Map<Long, String> editais) {
        this.editais = editais;
    }

    public LinkedList<Projeto> getLista() {
        if (lista.isEmpty()) {
            return projeto.listar();
        }
        return lista;
    }   

    public String cadastrar(){
        lista.add(projeto);
        return "listarProjetos?faces-redirect=true";
    }

    public String detalhar(Projeto projeto) {
        this.projetoSelecionado = projeto;
        return "detalharProjeto?faces-redirect=true";
    }
    
    public String editar(Projeto projeto) {
        this.projetoSelecionado = projeto;
        return "editarProjeto?faces-redirect=true";
    }

    public String gravar(Projeto projeto) {
        int i;
        for(i = 0; i < lista.size(); i++){
            if(lista.get(i).getTitulo().equals(projeto.getTitulo())){
                lista.set(i, projeto);
            }
        }
        return "listarProjetos?faces-redirect=true";
    }

    public String limpar(){
        this.projeto = new Projeto();
        return "listarProjetos?faces-redirect=true";
    }

    public String remover(Projeto projeto) {
        lista.remove(projeto);
        return "listarProjetos?faces-redirect=true";
    }

    public String salvar(Projeto projeto){
        lista.add(projeto);
        return "listarProjetos?faces-redirect=true";
    }

    public String voltar(){
        return "listarProjetos?faces-redirect=true";
    }
}
