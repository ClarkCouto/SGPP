package beans;

import entities.Coordenador;
import entities.GrupoDePesquisa;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author CristianoSilva
 */
@ManagedBean
@SessionScoped
public class GrupoDePesquisaBean {
    private GrupoDePesquisa grupoDePesquisa = new GrupoDePesquisa();
    private GrupoDePesquisa grupoDePesquisaSelecionado;
    private List<Coordenador> coordenadores;
    private List<GrupoDePesquisa> gruposDePesquisa;
    private List<GrupoDePesquisa> listaFiltrada;
    
// Getters e Setters
    public List<GrupoDePesquisa> getListaFiltrada() {
        return this.listaFiltrada;
    }
 
    public void setListaFiltrada(List<GrupoDePesquisa> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public List<SelectItem> getCoordenadores(){
        List<SelectItem> items = new ArrayList<>();  
        new Coordenador().buscarTodosCoordenadores().forEach((c) -> {
            items.add(new SelectItem(c, c.getNome()));
        }); 
        return items;
    }
    
    public GrupoDePesquisa getGrupoDePesquisa() {
        return this.grupoDePesquisa;
    }

    public void setGrupoDePesquisa(GrupoDePesquisa grupoDePesquisa) {
        this.grupoDePesquisa = grupoDePesquisa;
    }

    public void setGrupoDePesquisaSelecionado(GrupoDePesquisa grupoDePesquisa) {
        this.grupoDePesquisaSelecionado = grupoDePesquisa;
    }
      
    public List<GrupoDePesquisa> getGruposDePesquisa(){
        this.gruposDePesquisa = this.grupoDePesquisa.buscarTodos();
        return gruposDePesquisa;
    }
    
    public void setGruposDePesquisa(List<GrupoDePesquisa> lista){
        this.gruposDePesquisa = lista;
    }
    
// Ações
    public String detalhar(Long id){
        if(id != null)
            this.grupoDePesquisaSelecionado = this.grupoDePesquisa.buscarPeloId(id);

        if (this.grupoDePesquisaSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Grupo De Pesquisa!",
                                   "Erro ao localizar Grupo De Pesquisa!"));
            return "/pages/listar/listarGruposDePesquisa";
        }
        else {
            this.grupoDePesquisa = this.grupoDePesquisaSelecionado;
            return "/pages/detalhes/detalhesGrupoDePesquisa?faces-redirect=true";
        }
    }
    
    public String editar(Long id){
        if(id != null)
            this.grupoDePesquisaSelecionado = this.grupoDePesquisa.buscarPeloId(id);

        if (this.grupoDePesquisaSelecionado == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao localizar Grupo De Pesquisa!",
                                   "Erro ao localizar Grupo De Pesquisa!"));
            return "/pages/listar/listarGruposDePesquisa";
        }
        else {
            this.grupoDePesquisa = this.grupoDePesquisaSelecionado;
            return "/pages/editar/editarGrupoDePesquisa?faces-redirect=true";
        }
    }  
    
    public void limpar(){
        this.grupoDePesquisa = new GrupoDePesquisa();
        this.grupoDePesquisaSelecionado = new GrupoDePesquisa();
    }
    
    public String remover(Long id) {
        if(this.grupoDePesquisa.remover(id))
            return "/pages/listar/listarGruposDePesquisa?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Grupo De Pesquisa!",
                                   "Erro ao excluir Grupo De Pesquisa!"));
            return "/pages/listar/listarGruposDePesquisa";
        }
    }
    
    public String salvar() {
        if(this.grupoDePesquisa.salvar())
            return "/pages/listar/listarGruposDePesquisa?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Grupo De Pesquisa!",
                                   "Erro ao salvar Grupo De Pesquisa!"));
            return "/pages/cadastrar/cadastrarGrupoDePesquisa";
        }
    }
}

