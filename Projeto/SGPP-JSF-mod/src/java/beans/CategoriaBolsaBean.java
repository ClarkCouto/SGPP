package beans;

import entities.CategoriaBolsa;
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
public class CategoriaBolsaBean {
    private CategoriaBolsa categoriaBolsa = new CategoriaBolsa();
    private CategoriaBolsa categoriaBolsaSelecionada;
    private List<CategoriaBolsa> categoriaBolsas;
    private Boolean editando;
    
// Getters e Setters
    public CategoriaBolsa getCategoriaBolsa() {
        return categoriaBolsa;
    }

    public void setCategoriaBolsa(CategoriaBolsa categoriaBolsa) {
        this.categoriaBolsa = categoriaBolsa;
    }

    public void setCategoriaBolsaSelecionado(CategoriaBolsa categoriaBolsa) {
        this.categoriaBolsaSelecionada = categoriaBolsa;
    }
      
    public List<CategoriaBolsa> getCategoriaBolsas(){
        this.categoriaBolsas = this.categoriaBolsa.buscarTodos();
        return categoriaBolsas;
    }
    
    public void setCategoriaBolsas(List<CategoriaBolsa> lista){
        this.categoriaBolsas = lista;
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
            categoriaBolsaSelecionada = this.categoriaBolsa.buscarPeloId(id);

        if (categoriaBolsaSelecionada != null) {
            this.categoriaBolsa = categoriaBolsaSelecionada;
            this.editando = Boolean.TRUE;
        } else {
            this.editando = Boolean.FALSE;
        }
        return "editarCategoriaBolsa";
    }  
    
    public void limpar(){
        this.editando = false;
        this.categoriaBolsa = new CategoriaBolsa();
        this.categoriaBolsaSelecionada = new CategoriaBolsa();
    }
    
    public String remover(Long id) {
        if(categoriaBolsa.remover(id))
            return "listarCategoriasBolsa?faces-redirect=true";
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao exluir Categoria!",
                                   "Erro ao excluir Categoria!"));
            return "listarCategoriasBolsa";
        }
    }
    
    public String salvar() {
        if(categoriaBolsa.salvar())
            return "listarCategoriasBolsa?faces-redirect=true";
        else {
            FacesContext.getCurrentInstance().addMessage(null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar Categoria!",
                                   "Erro ao salvar Categoria!"));
            return "cadastrarCategoriaBolsa";
        }
    }
}

