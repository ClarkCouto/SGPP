package beans;

import entities.GrupoDePesquisa;
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
    private List<GrupoDePesquisa> grupoDePesquisas;
    private Boolean editando;
    
// Getters e Setters
    public GrupoDePesquisa getGrupoDePesquisa() {
        return grupoDePesquisa;
    }

    public void setGrupoDePesquisa(GrupoDePesquisa grupoDePesquisa) {
        this.grupoDePesquisa = grupoDePesquisa;
    }

    public void setGrupoDePesquisaSelecionado(GrupoDePesquisa grupoDePesquisa) {
        this.grupoDePesquisaSelecionado = grupoDePesquisa;
    }
      
    public List<GrupoDePesquisa> getGrupoDePesquisas(){
        this.grupoDePesquisas = this.grupoDePesquisa.buscarTodos();
        return grupoDePesquisas;
    }
    
    public void setGrupoDePesquisas(List<GrupoDePesquisa> lista){
        this.grupoDePesquisas = lista;
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
        return "editarGrupoDePesquisa";
    }  
    
    public void limpar(){
        this.editando = false;
        this.grupoDePesquisa = new GrupoDePesquisa();
        this.grupoDePesquisaSelecionado = new GrupoDePesquisa();
    }
    
    public String remover(Long id) {
        if(grupoDePesquisa.remover(id))
            return "listarGrupoDePesquisas?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir GrupoDePesquisa!",
                                   "Erro ao excluir GrupoDePesquisa!"));
            return "listarGrupoDePesquisas";
        }
    }
    
    public String salvar() {
        if(grupoDePesquisa.salvar())
            return "listarGrupoDePesquisas?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar GrupoDePesquisa!",
                                   "Erro ao salvar GrupoDePesquisa!"));
            return "cadastrarGrupoDePesquisas";
        }
    }
}

