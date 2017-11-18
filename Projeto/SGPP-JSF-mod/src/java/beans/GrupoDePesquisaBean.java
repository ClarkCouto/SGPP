package beans;

import entities.Coordenador;
import entities.GrupoDePesquisa;
import entities.Instituicao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
    private Boolean editando;
    
// Getters e Setters
    public List<GrupoDePesquisa> getListaFiltrada() {
        return listaFiltrada;
    }
 
    public void setListaFiltrada(List<GrupoDePesquisa> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public List<Coordenador> getCoordenadores(){
        this.coordenadores = (List<Coordenador>) (Coordenador) new Coordenador().buscarTodos();
        return coordenadores;
    }
    
    public GrupoDePesquisa getGrupoDePesquisa() {
        return grupoDePesquisa;
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

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
// Ações
    public String editar(Long id){
        if(id != null)
            grupoDePesquisaSelecionado = this.grupoDePesquisa.buscarPeloId(id);

        if (grupoDePesquisaSelecionado != null) {
            this.grupoDePesquisa = grupoDePesquisaSelecionado;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "/pages/grupoDePesquisa/editarGrupoDePesquisa";
    }  
    
    public void limpar(){
        this.editando = false;
        this.grupoDePesquisa = new GrupoDePesquisa();
        this.grupoDePesquisaSelecionado = new GrupoDePesquisa();
    }
    
    public String remover(Long id) {
        if(grupoDePesquisa.remover(id))
            return "/pages/grupoDePesquisa/listarGrupoDePesquisas?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir GrupoDePesquisa!",
                                   "Erro ao excluir GrupoDePesquisa!"));
            return "/pages/grupoDePesquisa/listarGrupoDePesquisas";
        }
    }
    
    public String salvar() {
        if(grupoDePesquisa.salvar())
            return "/pages/grupoDePesquisa/listarGrupoDePesquisas?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar GrupoDePesquisa!",
                                   "Erro ao salvar GrupoDePesquisa!"));
            return "/pages/grupoDePesquisa/cadastrarGrupoDePesquisas";
        }
    }
}

